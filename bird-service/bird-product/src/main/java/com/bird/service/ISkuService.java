package com.bird.service;

import com.bird.entity.PageVo;
import com.bird.entity.product.Sku;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @Author lipu
 * @Date 2020/10/26 9:24
 * @Description
 */
public interface ISkuService {

    List<Sku> findAll(PageVo pageVo);

    List<Sku> selectList();

    Sku findById(Long id);

    Sku details(Long id) throws Exception;

}
