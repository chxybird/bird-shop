package com.bird.service;

import com.bird.entity.PageVo;
import com.bird.entity.product.Spu;

import java.util.List;

/**
 * @Author lipu
 * @Date 2020/10/23 14:25
 * @Description
 */
public interface ISpuService {

    void add(Spu spu);

    List<Spu> findAll(PageVo pageVo);

    Integer putStatus(Integer status,Long id);

    Integer update(Spu spu);

    Integer deleteById(Long id);
}
