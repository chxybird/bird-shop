package com.bird.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bird.dao.ICategoryDao;
import com.bird.domain.Category;
import com.bird.service.ICategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author lipu
 * @Date 2020/9/30 17:28
 * @Description
 */
@Service
public class CategoryService implements ICategoryService {

    @Resource
    private ICategoryDao categoryDao;

    /**
     * @Author lipu
     * @Date 2020/9/30 17:29
     * @Description 根据父id查询所有分类信息
     */
    @Override
    public List<Category> findByParentId(Long parentId) {
        List<Category> categoryList = categoryDao.selectList(new QueryWrapper<Category>().eq("parent_id", parentId));
        return categoryList;
    }
}
