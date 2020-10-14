package com.bird.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bird.dao.IBrandDao;
import com.bird.entity.Brand;
import com.bird.entity.PageVo;
import com.bird.service.IBrandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
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
}
