package com.bird.service;

import com.bird.entity.user.Staff;
import com.bird.entity.vo.LoginVo;
import com.bird.entity.vo.RegisterVo;

import java.util.List;

/**
 * @Author lipu
 * @Date 2020/10/26 17:12
 * @Description
 */
public interface IStaffService {

    Staff findById(Long id);

    List<Staff> selectList();

    Boolean register(RegisterVo registerVo);

    Boolean login(LoginVo loginVo);
}
