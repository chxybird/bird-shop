package com.bird.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bird.dao.*;
import com.bird.entity.PageVo;
import com.bird.entity.product.*;
import com.bird.entity.product.relation.SkuAttrValue;
import com.bird.entity.product.relation.SpuAttrValue;
import com.bird.service.ISkuService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * @Author lipu
 * @Date 2020/10/26 9:24
 * @Description
 */
@Service
public class SkuService implements ISkuService {
    @Resource
    private ISkuDao skuDao;
    @Resource
    private IBrandDao brandDao;
    @Resource
    private ICategoryDao categoryDao;
    @Resource
    private ISpuDao spuDao;
    @Resource
    private ISpuAttrValueDao spuAttrValueDao;
    @Resource
    private ISkuAttrValueDao skuAttrValueDao;
    @Resource
    private IAttrDao attrDao;
    @Resource
    private IImagDao imagDao;
    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    /**
     * @Author lipu
     * @Date 2020/10/26 10:05
     * @Description 查询所有商品信息
     */
    @Override
    public List<Sku> findAll(PageVo pageVo) {
        IPage<Sku> skuIPage=new Page<>(pageVo.getPage(),pageVo.getSize());
        QueryWrapper<Sku> queryWrapper=new QueryWrapper<>();
        if (!StringUtils.isEmpty(pageVo.getKey())){
            queryWrapper.and((obj)->{
                return obj.eq("id",pageVo.getKey()).or().like("name",pageVo.getKey());
            });
        }
        return skuDao.selectPage(skuIPage,queryWrapper).getRecords();
    }

    /**
     * @Author lipu
     * @Date 2020/10/31 13:12
     * @Description 获取商品下拉列表
     */
    @Override
    public List<Sku> selectList() {
        List<Sku> selectList = skuDao.selectList(null);
        return selectList;
    }

    /**
     * @Author lipu
     * @Date 2020/10/31 14:53
     * @Description 根据id查询商品信息
     */
    @Override
    public Sku findById(Long id) {
        Sku sku = skuDao.selectOne(new QueryWrapper<Sku>().eq("id", id));
        return sku;
    }

    /**
     * @Author lipu
     * @Date 2020/12/18 13:57
     * @Description 商品详情
     */
    @Override
    public Sku details(Long id) throws Exception {
        //主任务一 获取sku信息
        CompletableFuture<Sku> mainTaskOne = CompletableFuture.supplyAsync(() -> {
            Sku sku = skuDao.selectById(id);
            return sku;
        }, threadPoolExecutor);
        //主任务二获取spu信息,此任务必须等任务一执行完毕才可以正确执行
        CompletableFuture<Spu> mainTaskTwo = mainTaskOne.thenApply((sku -> {
            Spu spu = spuDao.selectOne(new QueryWrapper<Spu>()
                    .eq("id", sku.getSpuId()));
            sku.setSpu(spu);
            return spu;
        }));
        CompletableFuture<Void>  mainTaskOneSubOne= mainTaskOne.thenAcceptAsync((sku) -> {
            //查询图片信息
            List<Image> imageList = imagDao.selectList(new QueryWrapper<Image>().eq("sku_id", sku.getId()));
            sku.setImageList(imageList);
        });
        CompletableFuture<Void> mainTaskOneSubTwo = mainTaskOne.thenAcceptAsync((sku) -> {
            //查询销售信息
            List<SkuAttrValue> skuAttrValueList = skuAttrValueDao.selectList(new QueryWrapper<SkuAttrValue>()
                    .eq("sku_id", sku.getId()));
            List<Long> attrSkuIdList = skuAttrValueList.stream().map(SkuAttrValue::getAttrId).collect(Collectors.toList());
            List<Attr> attrSkuList = attrDao.selectBatchIds(attrSkuIdList);
            sku.setSkuAttrValueList(skuAttrValueList);
            sku.setAttrList(attrSkuList);
        });
        CompletableFuture<Spu> mainTaskTwoSubOne = mainTaskTwo.thenApply((spu) -> {
            Brand brand = brandDao.selectOne(new QueryWrapper<Brand>()
                    .eq("id", spu.getBrandId()));
            spu.setBrand(brand);
            return spu;
        });
        CompletableFuture<Spu> mainTaskTwoSubTwo = mainTaskTwo.thenApply((spu -> {
            Category category = categoryDao.selectOne(new QueryWrapper<Category>()
                    .eq("id", spu.getCategoryId()));
            spu.setCategory(category);
            return spu;
        }));
        CompletableFuture<Void> mainTaskTwoSubThree = mainTaskTwo.thenAcceptAsync((spu -> {
            //查询基本信息
            List<SpuAttrValue> spuAttrValueList = spuAttrValueDao.selectList(new QueryWrapper<SpuAttrValue>()
                    .eq("spu_id", spu.getId()));
            List<Long> attrSpuIdList = spuAttrValueList.stream().map(SpuAttrValue::getAttrId).collect(Collectors.toList());
            List<Attr> attrSpuList = attrDao.selectBatchIds(attrSpuIdList);
            spu.setSpuAttrValueList(spuAttrValueList);
            spu.setAttrList(attrSpuList);
        }));
        CompletableFuture<Void> allOf = CompletableFuture.allOf(mainTaskOneSubOne,mainTaskOneSubTwo,
                mainTaskTwoSubOne,mainTaskTwoSubTwo,mainTaskTwoSubThree);
        allOf.get();
        return mainTaskOne.get();
    }

    /**
     * @Author lipu
     * @Date 2021/1/24 13:44
     * @Description 批量查询商品信息
     */
    @Override
    public List<Sku> findBatch(List<Long> idList) {
        List<Sku> skuList = skuDao.selectBatchIds(idList);
        return skuList;
    }
}
