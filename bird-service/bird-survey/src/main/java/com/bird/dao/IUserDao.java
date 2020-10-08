package com.bird.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bird.domain.User;
import org.springframework.stereotype.Repository;

/**
 * @Author lipu
 * @Date 2020/10/8 11:22
 * @Description
 */
@Repository
public interface IUserDao extends BaseMapper<User> {
}
