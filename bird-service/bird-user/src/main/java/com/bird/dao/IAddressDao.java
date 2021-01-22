package com.bird.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bird.entity.user.Address;
import org.springframework.stereotype.Repository;

/**
 * @Author lipu
 * @Date 2021/1/22 13:45
 * @Description
 */
@Repository
public interface IAddressDao extends BaseMapper<Address> {
}
