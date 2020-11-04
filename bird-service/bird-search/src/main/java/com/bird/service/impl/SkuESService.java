package com.bird.service.impl;

import com.bird.dao.ISkuESDao;
import com.bird.domain.SkuModel;
import com.bird.service.ISkuESService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author lipu
 * @Date 2020/11/1 19:21
 * @Description
 */
@Service
public class SkuESService implements ISkuESService {
    @Resource
    private ISkuESDao skuESDao;

    /**
     * @Author lipu
     * @Date 2020/11/1 19:21
     * @Description 批量添加商品信息到ES库
     */
    @Override
    public Boolean saveAll(List<SkuModel> skuModelList) {
        Iterable<SkuModel> iterable = skuESDao.saveAll(skuModelList);
        if (iterable.iterator().hasNext()){
            return true;
        }else {
            return false;
        }
    }

    /**
     * @Author lipu
     * @Date 2020/11/3 10:36
     * @Description 批量删除ES库的商品信息
     */
    @Override
    public Boolean deleteBatch(List<SkuModel> skuModelList) {
        try{
            skuESDao.deleteAll(skuModelList);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
