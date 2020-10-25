package com.bird.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bird.dao.IBrandCategoryDao;
import com.bird.dao.IBrandDao;
import com.bird.dao.ICategoryDao;
import com.bird.entity.product.Brand;
import com.bird.entity.PageVo;
import com.bird.entity.product.Category;
import com.bird.entity.product.relation.BrandCategory;
import com.bird.service.IBrandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author lipu
 * @Date 2020/10/10 14:17
 * @Description
 */
@Service
@Transactional
public class BrandService implements IBrandService {
    @Resource
    private IBrandDao brandDao;
    @Resource
    private IBrandCategoryDao brandCategoryDao;
    @Resource
    private ICategoryDao categoryDao;

    /**
     * @Author lipu
     * @Date 2020/10/10 14:24
     * @Description 查询所有品牌信息 分页查询
     */
    @Override
    public List<Brand> findAll(PageVo pageVo) {
        IPage<Brand> brandIPage = new Page<>(pageVo.getPage(), pageVo.getSize());
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(pageVo.getKey())) {
            queryWrapper.eq("id", pageVo.getKey()).or().like("name", pageVo.getKey())
                    .or().eq("letter", pageVo.getKey());
        }
        queryWrapper.orderByAsc("sort");
        IPage<Brand> selectPage = brandDao.selectPage(brandIPage, queryWrapper);
        List<Brand> brandList = selectPage.getRecords();
        return brandList;
    }

    /**
     * @Author lipu
     * @Date 2020/10/10 14:25
     * @Description 根据id查询品牌信息
     */
    @Override
    public Brand findById(Long id) {
        return brandDao.selectOne(new QueryWrapper<Brand>().eq("id", id));
    }

    /**
     * @Author lipu
     * @Date 2020/10/10 14:25
     * @Description 添加品牌信息
     */
    @Override
    public Integer add(Brand brand) {
        return brandDao.insert(brand);
    }

    /**
     * @Author lipu
     * @Date 2020/10/10 14:25
     * @Description 更新品牌信息
     */
    @Override
    public Integer update(Brand brand) {
        UpdateWrapper<Brand> updateWrapper = new UpdateWrapper<Brand>().eq("id", brand.getId());
        Integer result = brandDao.update(brand, updateWrapper);
        return result;
    }

    /**
     * @Author lipu
     * @Date 2020/10/10 14:26
     * @Description 根据id删除品牌信息
     */
    @Override
    public Integer deleteById(Long id) {
        return brandDao.deleteById(id);
    }

    /**
     * @Author lipu
     * @Date 2020/10/10 14:26
     * @Description 批量删除品牌信息
     */
    @Override
    public void deleteBatch(List<Brand> brandList) {
        for (Brand brand : brandList) {
            brandDao.deleteById(brand.getId());
        }
    }

    /**
     * @Author lipu
     * @Date 2020/10/16 9:33
     * @Description 新增品牌分类关联
     */
    @Override
    public Integer addToCategory(BrandCategory brandCategory) {
        Integer result = brandCategoryDao.insert(brandCategory);
        return result;
    }

    /**
     * @Author lipu
     * @Date 2020/10/15 14:10
     * @Description 根据品牌id查询分类信息
     */
    @Override
    public List<Category> findCategory(Long brandId) {
        List<BrandCategory> brandCategoryList = brandCategoryDao.selectList(new QueryWrapper<BrandCategory>().eq("brand_id", brandId));
        List<Long> categoryIdList = new ArrayList<>();
        for (BrandCategory brandCategory:brandCategoryList) {
            categoryIdList.add(brandCategory.getCategoryId());
        }
        List<Category> categoryList = categoryDao.selectBatchIds(categoryIdList);
        return categoryList;
    }

    /**
     * @Author lipu
     * @Date 2020/10/16 9:52
     * @Description 移除关联的分类
     */
    @Override
    public Integer removeCategory(BrandCategory brandCategory) {
        QueryWrapper<BrandCategory> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("brand_id",brandCategory.getBrandId())
                .eq("category_id",brandCategory.getCategoryId());
        brandCategoryDao.delete(queryWrapper);
        return null;
    }

    /**
     * @Author lipu
     * @Date 2020/10/23 9:44
     * @Description 根据分类id查询品牌信息
     */
    @Override
    public List<Brand> findByCategoryId(Long categoryId) {
        List<BrandCategory> brandCategoryList = brandCategoryDao.selectList(
                new QueryWrapper<BrandCategory>().eq("category_id", categoryId));
        if (brandCategoryList.size()>0){
            List<Long> idList=new ArrayList<>();
            for (BrandCategory brandCategory:brandCategoryList) {
                idList.add(brandCategory.getBrandId());
            }
            List<Brand> brandList = brandDao.selectBatchIds(idList);
            return brandList;
        }else {
            return null;
        }
    }
}
