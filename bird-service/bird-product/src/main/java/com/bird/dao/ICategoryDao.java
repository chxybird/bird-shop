package com.bird.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bird.entity.Category;
import org.springframework.stereotype.Repository;

/**
 * @Author lipu
 * @Date 2020/9/30 17:21
 * @Description
 */
@Repository
public interface ICategoryDao extends BaseMapper<Category> {
}
