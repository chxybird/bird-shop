package com.bird.service;

import com.bird.entity.PageVo;
import com.bird.entity.ware.Ware;

import java.util.List;

/**
 * @Author lipu
 * @Date 2020/10/26 11:24
 * @Description
 */
public interface IWareService {

    public Integer add(Ware ware);

    List<Ware> findAll(PageVo pageVo);

    List<Ware> selectList();
}
