package com.bird.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bird.dao.IStaffDao;
import com.bird.entity.user.Staff;
import com.bird.service.IStaffService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author lipu
 * @Date 2020/10/26 17:13
 * @Description
 */
@Service
@Transactional
public class StaffService implements IStaffService {
    @Resource
    private IStaffDao staffDao;

    /**
     * @Author lipu
     * @Date 2020/10/26 17:13
     * @Description 根据id查询用户信息
     */
    @Override
    public Staff findById(Long id) {
        Staff staff = staffDao.selectOne(new QueryWrapper<Staff>().eq("id", id));
        return staff;
    }
}
