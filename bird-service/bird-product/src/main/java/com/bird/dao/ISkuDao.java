package com.bird.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bird.entity.product.Sku;
import org.springframework.stereotype.Repository;

/**
 * @Author lipu
 * @Date 2020/10/23 14:58
 * @Description
 */
@Repository
public interface ISkuDao extends BaseMapper<Sku> {
}
