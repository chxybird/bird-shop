package com.bird.service;

import com.bird.entity.user.Address;

import java.util.List;

/**
 * @Author lipu
 * @Date 2021/1/22 13:39
 * @Description
 */
public interface IAddressService {

    void add(Address address);

    void delete(Long id);

    List<Address> findByStaffId(Long staffId);
}
