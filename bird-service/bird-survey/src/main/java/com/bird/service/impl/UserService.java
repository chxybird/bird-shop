package com.bird.service.impl;

import com.bird.dao.IUserDao;
import com.bird.domain.User;
import com.bird.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author lipu
 * @Date 2020/10/8 11:23
 * @Description
 */
@Service
public class UserService implements IUserService {
    @Resource
    private IUserDao userDao;
    /**
     * @Author lipu
     * @Date 2020/10/8 11:23
     * @Description 查询所有用户信息
     */
    @Override
    public List<User> findAll() {
        List<User> userList = userDao.selectList(null);
        return userList;
    }

}
