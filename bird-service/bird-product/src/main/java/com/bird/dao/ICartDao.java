package com.bird.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bird.entity.product.Cart;
import org.springframework.stereotype.Repository;

/**
 * @Author lipu
 * @Date 2020/12/24 11:30
 * @Description
 */
@Repository
public interface ICartDao extends BaseMapper<Cart> {
}
