package com.bird.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bird.dao.ICategoryDao;
import com.bird.entity.Category;
import com.bird.service.ICategoryService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author lipu
 * @Date 2020/9/30 17:28
 * @Description
 */
@Service
@CacheConfig(cacheNames = "category", cacheManager = "cacheManager")
@Transactional
public class CategoryService implements ICategoryService {

    @Resource
    private ICategoryDao categoryDao;

    /**
     * @Author lipu
     * @Date 2020/9/30 17:29
     * @Description 根据父id查询所有分类信息
     */
    @Override
    @Cacheable(key = "'findByParentId'+#parentId")
    public List<Category> findByParentId(Long parentId) {
        List<Category> categoryList = categoryDao.selectList(new QueryWrapper<Category>().eq("parent_id", parentId));
        return categoryList;
    }

    /**
     * @Author lipu
     * @Date 2020/10/8 13:38
     * @Description 根据主键查询分类信息
     */
    @Override
    @Cacheable(key = "'findById'+#id")
    public Category findById(Long id) {
        Category category = categoryDao.selectOne(new QueryWrapper<Category>().eq("id", id));
        return category;
    }

    /**
     * @Author lipu
     * @Date 2020/10/8 14:53
     * @Description 插入一条分类信息
     */
    @Override
    @CacheEvict(value="category", allEntries=true)
    public Integer add(Category category) {
        Integer result = categoryDao.insert(category);
        return result;
    }

    /**
     * @Author lipu
     * @Date 2020/10/8 17:16
     * @Description 更新分类信息
     */
    @Override
    @CacheEvict(value="category", allEntries=true)
    public Integer update(Category category){
        Integer result = categoryDao.update(category, new UpdateWrapper<Category>().eq("id", category.getId()));
        return result;
    }

    /**
     * @Author lipu
     * @Date 2020/10/8 17:20
     * @Description 根据id删除分类信息
     */
    @Override
    @CacheEvict(value="category", allEntries=true)
    public Integer deleteById(Long id){
        int result = categoryDao.deleteById(id);
        return result;
    }

    /**
     * @Author lipu
     * @Date 2020/10/8 20:00
     * @Description 批量删除分类
     */
    @Override
    @CacheEvict(value="category", allEntries=true)
    public void deleteBatch(List<Category> categoryList){
        List<Long> idList=new ArrayList<>();
        //获取批量删除id的集合
        for (Category category:categoryList) {
            idList.add(category.getId());
        }
        categoryDao.deleteBatchIds(idList);
    }

}
