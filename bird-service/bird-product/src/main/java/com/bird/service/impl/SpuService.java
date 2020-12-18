package com.bird.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bird.common.CommonResult;
import com.bird.dao.*;
import com.bird.entity.PageVo;
import com.bird.entity.product.*;
import com.bird.entity.product.status.SpuStatus;
import com.bird.entity.product.es.SkuModel;
import com.bird.entity.product.relation.SkuAttrValue;
import com.bird.entity.product.relation.SpuAttrValue;
import com.bird.feign.ISkuESFeign;
import com.bird.service.ISpuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author lipu
 * @Date 2020/10/23 14:25
 * @Description
 */
@Service
@Transactional
public class SpuService implements ISpuService {
    @Resource
    private ISpuDao spuDao;
    @Resource
    private ISkuDao skuDao;
    @Resource
    private ISpuAttrValueDao spuAttrValueDao;
    @Resource
    private ISkuAttrValueDao skuAttrValueDao;
    @Resource
    private IImagDao imagDao;
    @Resource
    private ICategoryDao categoryDao;
    @Resource
    private IBrandDao brandDao;
    @Resource
    private ISkuESFeign skuESFeign;

    /**
     * @Author lipu
     * @Date 2020/10/23 15:17
     * @Description 添加商品
     */
    @Override
    public void add(Spu spu) {
        //添加Spu信息
        spuDao.insert(spu);
        //添加基本属性值信息
        List<SpuAttrValue> spuAttrValueList = spu.getSpuAttrValueList();
        for (SpuAttrValue spuAttrValue : spuAttrValueList) {
            spuAttrValue.setSpuId(spu.getId());
            spuAttrValueDao.insert(spuAttrValue);
        }
        //添加Sku信息
        List<Sku> skuList = spu.getSkuList();
        for (Sku sku : skuList) {
            sku.setSpuId(spu.getId());
            skuDao.insert(sku);
            //添加Sku的图片集信息
            List<Image> imageList = sku.getImageList();
            for (Image image : imageList) {
                image.setSkuId(sku.getId());
                imagDao.insert(image);
            }
            //设置销售属性信息
            List<SkuAttrValue> skuAttrValueList = sku.getSkuAttrValueList();
            for (SkuAttrValue skuAttrValue : skuAttrValueList) {
                skuAttrValue.setSkuId(sku.getId());
                skuAttrValueDao.insert(skuAttrValue);
            }
        }
    }

    /**
     * @Author lipu
     * @Date 2020/10/25 20:09
     * @Description 查询所有商品信息
     */
    @Override
    public List<Spu> findAll(PageVo pageVo) {
        IPage<Spu> spuIPage = new Page<>(pageVo.getPage(), pageVo.getSize());
        QueryWrapper<Spu> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(pageVo.getKey())) {
            queryWrapper.and((obj) -> {
                return obj.eq("id", pageVo.getKey()).or().like("name", pageVo.getKey());
            });
        }
        List<Spu> spuList = spuDao.selectPage(spuIPage, queryWrapper).getRecords();
        spuList.forEach((spu -> {
            Brand brand = brandDao.selectOne(new QueryWrapper<Brand>().eq("id", spu.getBrandId()));
            Category category = categoryDao.selectOne(
                    new QueryWrapper<Category>().eq("id", spu.getCategoryId()));
            spu.setBrandName(brand.getName());
            spu.setCategoryName(category.getName());
        }));
        return spuList;
    }

    /**
     * @Author lipu
     * @Date 2020/10/25 20:33
     * @Description 商品上架下架
     */
    @Override
    public Integer putStatus(Integer status, Long id) {
        QueryWrapper<Spu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        Spu spu = spuDao.selectOne(queryWrapper);
        Brand brand = brandDao.selectOne(new QueryWrapper<Brand>().eq("id", spu.getBrandId()));
        spu.setBrandName(brand.getName());
        Category category = categoryDao.selectOne(new QueryWrapper<Category>().eq("id", spu.getCategoryId()));
        spu.setCategoryName(category.getName());
        spu.setStatus(status);
        //修改商品状态
        Integer result = spuDao.update(spu, queryWrapper);
        List<Sku> skuList = skuDao.selectList(
                new QueryWrapper<Sku>().eq("spu_id", spu.getId()));
        //ES数据库同步我使用双写策略
        if (result > 0) {
            //判断商品是上架还是下架 上架入ES库下架出ES库
            //入库
            if (status == SpuStatus.PUBLISH.getStatus()) {
                //商品写入es
                List<SkuModel> skuModelList = new ArrayList<>();
                skuList.forEach((sku -> {
                    //获取每个sku对应的属性以及
                    SkuModel skuModel = new SkuModel();
                    skuModel.setId(sku.getId());
                    skuModel.setBrandName(spu.getBrandName());
                    skuModel.setCategoryName(spu.getCategoryName());
                    skuModel.setDescription(sku.getDescription());
                    skuModel.setPrice(sku.getPrice());
                    skuModel.setName(sku.getName());
                    skuModel.setTitle(sku.getTitle());
                    skuModelList.add(skuModel);
                }));
                //调用搜索微服务商品信息入库
                CommonResult<Integer> flag = skuESFeign.saveAll(skuModelList);
                return flag.getData();
            } else { //出库
                List<SkuModel> skuModelList = skuList.stream().map(sku -> {
                    SkuModel skuModel = new SkuModel();
                    skuModel.setId(sku.getId());
                    return skuModel;
                }).collect(Collectors.toList());
                return skuESFeign.deleteBatch(skuModelList).getData();
            }
        } else {
            return 0;
        }
    }

    /**
     * @Author lipu
     * @Date 2020/10/26 9:14
     * @Description 更新商品信息
     */
    @Override
    public Integer update(Spu spu) {
        QueryWrapper<Spu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", spu.getId());
        return spuDao.update(spu, queryWrapper);
    }

    /**
     * @Author lipu
     * @Date 2020/10/26 9:14
     * @Description 根据id删除商品信息
     */
    @Override
    public Integer deleteById(Long id) {
        return spuDao.deleteById(id);
    }
}
