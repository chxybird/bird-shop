package com.bird.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bird.component.SmsComponent;
import com.bird.dao.IStaffDao;
import com.bird.entity.user.Staff;
import com.bird.entity.vo.LoginVo;
import com.bird.entity.vo.RegisterVo;
import com.bird.service.IStaffService;
import com.bird.utils.BCrypt;
import io.lettuce.core.ScriptOutputType;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author lipu
 * @Date 2020/10/26 17:13
 * @Description
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StaffService implements IStaffService {
    @Resource
    private IStaffDao staffDao;
    @Resource
    private SmsComponent smsComponent;

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

    /**
     * @Author lipu
     * @Date 2020/10/27 10:37
     * @Description 获取用户下拉列表
     */
    @Override
    public List<Staff> selectList() {
        List<Staff> selectList = staffDao.selectList(null);
        return selectList;
    }

    /**
     * @Author lipu
     * @Date 2020/12/23 15:34
     * @Description 用户注册
     */
    @Override
    public Boolean register(RegisterVo registerVo) {
        //判断验证码是否正确
        Boolean checkMessage = smsComponent.checkMessage(registerVo.getCode(), registerVo.getPhone());
        if (checkMessage){
            Staff staff=new Staff();
            BeanUtils.copyProperties(registerVo,staff);
            //密码加盐加密
            String password = BCrypt.hashpw(staff.getPassword(), BCrypt.gensalt());
            staff.setPassword(password);
            staffDao.insert(staff);
            return true;
        }
        return false;
    }

    /**
     * @Author lipu
     * @Date 2020/12/23 16:11
     * @Description 用户登录
     */
    @Override
    public Boolean login(LoginVo loginVo) {
        Staff staff=new Staff();
        BeanUtils.copyProperties(loginVo,staff);
        Staff entity = staffDao.selectOne(new QueryWrapper<Staff>().eq("phone", staff.getPhone()));
        boolean check = BCrypt.checkpw(loginVo.getPassword(), entity.getPassword());
        if (check){
            return true;
        }else {
            return false;
        }
    }
}
