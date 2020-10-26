package com.bird.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bird.entity.user.Staff;
import org.springframework.stereotype.Repository;

/**
 * @Author lipu
 * @Date 2020/10/26 17:11
 * @Description
 */
@Repository
public interface IStaffDao extends BaseMapper<Staff> {
}
