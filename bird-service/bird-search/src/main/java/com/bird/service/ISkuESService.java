package com.bird.service;

import com.bird.domain.SkuModel;

import java.util.List;

/**
 * @Author lipu
 * @Date 2020/11/1 19:20
 * @Description
 */
public interface ISkuESService {


    Boolean saveAll(List<SkuModel> skuModelList);

    Boolean deleteBatch(List<SkuModel> skuModelList);
}
