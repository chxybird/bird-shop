package com.bird.service;

import com.bird.entity.vo.OrderCommitVo;

/**
 * @Author lipu
 * @Date 2021/1/23 22:03
 * @Description
 */
public interface IOrderService {
    void commit(OrderCommitVo orderCommitVo);
}
