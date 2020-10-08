package com.bird.service;

import com.bird.domain.User;

import java.util.List;

/**
 * @Author lipu
 * @Date 2020/10/8 11:21
 * @Description
 */
public interface IUserService {

    List<User> findAll();
}
