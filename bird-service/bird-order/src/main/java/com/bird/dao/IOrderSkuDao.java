package com.bird.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bird.entity.order.relation.OrderSku;
import org.springframework.stereotype.Repository;

/**
 * @Author lipu
 * @Date 2021/1/24 15:21
 * @Description
 */
@Repository
public interface IOrderSkuDao extends BaseMapper<OrderSku> {
}
