package com.bird.service;

import com.bird.entity.PageVo;
import com.bird.entity.ware.Purchase;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * @Author lipu
 * @Date 2020/10/26 15:36
 * @Description
 */
public interface IPurchaseService {

    Integer add(Purchase purchase);

    List<Purchase> findAll(PageVo pageVo);

    List<Purchase> selectList();

    public void mergePurchase(Purchase purchase);

}
