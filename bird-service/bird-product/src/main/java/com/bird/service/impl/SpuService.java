package com.bird.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bird.dao.*;
import com.bird.entity.PageVo;
import com.bird.entity.product.*;
import com.bird.entity.product.relation.SkuAttrValue;
import com.bird.entity.product.relation.SpuAttrValue;
import com.bird.service.ISpuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

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
            for (SkuAttrValue skuAttrValue:skuAttrValueList) {
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
        IPage<Spu> spuIPage=new Page<>(pageVo.getPage(),pageVo.getSize());
        QueryWrapper<Spu> queryWrapper=new QueryWrapper<>();
        if (!StringUtils.isEmpty(pageVo.getKey())){
            queryWrapper.and((obj)->{
                return obj.eq("id",pageVo.getKey()).or().like("name",pageVo.getKey());
            });
        }
        List<Spu> spuList = spuDao.selectPage(spuIPage, queryWrapper).getRecords();
        spuList.forEach((spu -> {
            Brand brand = brandDao.selectOne(new QueryWrapper<Brand>().eq("id", spu.getBrandId()));
            Category category = categoryDao.selectOne(new QueryWrapper<Category>().eq("id", spu.getCategoryId()));
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
    public Integer putStatus(Integer status,Long id) {
        QueryWrapper<Spu> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",id);
        Spu spu=new Spu();
        spu.setStatus(status);
        Integer result = spuDao.update(spu, queryWrapper);
        return result;
    }
}
