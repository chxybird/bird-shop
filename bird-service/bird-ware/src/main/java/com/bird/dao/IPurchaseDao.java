package com.bird.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bird.entity.ware.Purchase;
import org.springframework.stereotype.Repository;

/**
 * @Author lipu
 * @Date 2020/10/26 15:35
 * @Description
 */
@Repository
public interface IPurchaseDao extends BaseMapper<Purchase> {
}
