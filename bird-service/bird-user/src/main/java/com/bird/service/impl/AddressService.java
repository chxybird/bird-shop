package com.bird.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bird.dao.IAddressDao;
import com.bird.entity.user.Address;
import com.bird.service.IAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author lipu
 * @Date 2021/1/22 13:40
 * @Description
 */
@Service
public class AddressService implements IAddressService {
    @Resource
    private IAddressDao addressDao;

    /**
     * @Author lipu
     * @Date 2021/1/22 13:40
     * @Description 添加收货地址
     */
    @Override
    public void add(Address address) {
        addressDao.insert(address);
    }

    /**
     * @Author lipu
     * @Date 2021/1/22 13:48
     * @Description 删除收获地址
     */
    @Override
    public void delete(Long id) {
        addressDao.deleteById(id);
    }

    /**
     * @Author lipu
     * @Date 2021/1/22 13:49
     * @Description 查询所有收货地址信息
     */
    @Override
    public List<Address> findByStaffId(Long staffId) {
        List<Address> addressList = addressDao.selectList(new QueryWrapper<Address>()
                .eq("staff_id", staffId));
        return addressList;
    }
}
