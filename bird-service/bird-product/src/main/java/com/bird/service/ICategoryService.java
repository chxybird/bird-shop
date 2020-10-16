package com.bird.service;

import com.bird.entity.PageVo;
import com.bird.entity.product.Category;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @Author lipu
 * @Date 2020/9/30 17:22
 * @Description
 */
public interface ICategoryService {

    List<Category> findByParentId(Long parentId);

    Category findById(Long id);

    Integer add(Category category);

    Integer update(Category category);

    Integer deleteById(Long id);

    void deleteBatch(List<Category> categoryList);

}
