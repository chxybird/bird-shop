package com.bird.service;

import com.bird.entity.ware.Demand;
import com.bird.entity.ware.Purchase;

/**
 * @Author lipu
 * @Date 2020/10/26 15:43
 * @Description
 */
public interface IDemandService {

    void mergeToPurchase(Purchase purchase);

    Integer add(Demand demand);
}
