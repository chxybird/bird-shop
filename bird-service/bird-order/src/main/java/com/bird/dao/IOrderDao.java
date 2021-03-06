package com.bird.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bird.entity.order.Order;
import org.springframework.stereotype.Repository;

/**
 * @Author lipu
 * @Date 2020/10/14 9:42
 * @Description
 */
@Repository
public interface IOrderDao extends BaseMapper<Order>{
}
