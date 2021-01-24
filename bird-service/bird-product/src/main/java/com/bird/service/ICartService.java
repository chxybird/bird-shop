package com.bird.service;

import com.bird.entity.product.Cart;

/**
 * @Author lipu
 * @Date 2020/12/24 11:31
 * @Description
 */
public interface ICartService {

    void add(Cart cart);

    Cart findByStaffId(Long staffId);

    Boolean init(Long staffId);

    void delete(Long staffId);

    void update(Cart cart);

}
