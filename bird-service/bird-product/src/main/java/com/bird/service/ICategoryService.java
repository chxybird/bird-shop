package com.bird.service;

import com.bird.domain.Category;

import java.util.List;

/**
 * @Author lipu
 * @Date 2020/9/30 17:22
 * @Description
 */
public interface ICategoryService {

    List<Category> findByParentId(Long parentId);
}
