package com.bird.service.impl;

import com.bird.dao.IBrandDao;
import com.bird.entity.Brand;
import com.bird.entity.Page;
import com.bird.service.IBrandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Brand> findAll(Page page) {
        return null;
    }

    /**
     * @Author lipu
     * @Date 2020/10/10 14:25
     * @Description 根据id查询品牌信息
     */
    @Override
    public Brand findById(Long id) {
        return null;
    }

    /**
     * @Author lipu
     * @Date 2020/10/10 14:25
     * @Description 添加品牌信息
     */
    @Override
    public Integer add(Brand brand) {
        return null;
    }

    /**
     * @Author lipu
     * @Date 2020/10/10 14:25
     * @Description 更新品牌信息
     */
    @Override
    public Integer update(Brand brand) {
        return null;
    }

    /**
     * @Author lipu
     * @Date 2020/10/10 14:26
     * @Description 根据id删除品牌信息
     */
    @Override
    public Integer deleteById(Long id) {
        return null;
    }

    /**
     * @Author lipu
     * @Date 2020/10/10 14:26
     * @Description 批量删除品牌信息
     */
    @Override
    public void deleteBatch(List<Brand> brandList) {

    }
}
