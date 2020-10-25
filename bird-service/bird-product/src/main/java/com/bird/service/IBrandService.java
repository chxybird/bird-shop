package com.bird.service;

import com.bird.entity.product.Brand;
import com.bird.entity.PageVo;
import com.bird.entity.product.Category;
import com.bird.entity.product.relation.BrandCategory;
import org.springframework.web.bind.annotation.RequestParam;

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

    Integer addToCategory(BrandCategory brandCategory);

    List<Category> findCategory(Long brandId);

    Integer removeCategory(BrandCategory brandCategory);

    List<Brand> findByCategoryId(Long categoryId);

}
