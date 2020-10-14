package com.bird.service;

import com.bird.entity.Brand;
import com.bird.entity.PageVo;

import java.util.List;

/**
 * @Author lipu
 * @Date 2020/10/10 14:16
 * @Description
 */
public interface IBrandService {

    List<Brand> findAll(PageVo pageVo);

    Brand findById(Long id);

    Integer add(Brand brand);

    Integer update(Brand brand);

    Integer deleteById(Long id);

    void deleteBatch(List<Brand> brandList);

}
