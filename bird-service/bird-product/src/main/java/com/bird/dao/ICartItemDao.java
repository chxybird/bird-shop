package com.bird.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bird.entity.product.CartItem;
import org.springframework.stereotype.Repository;

/**
 * @Author lipu
 * @Date 2020/12/24 11:55
 * @Description
 */
@Repository
public interface ICartItemDao extends BaseMapper<CartItem> {
}
