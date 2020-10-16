package com.bird.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bird.entity.product.relation.BrandCategory;
import org.springframework.stereotype.Repository;

/**
 * @Author lipu
 * @Date 2020/10/15 14:37
 * @Description
 */
@Repository
public interface IBrandCategoryDao extends BaseMapper<BrandCategory> {
}
