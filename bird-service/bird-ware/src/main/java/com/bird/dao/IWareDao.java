package com.bird.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bird.entity.ware.Ware;
import org.springframework.stereotype.Repository;

/**
 * @Author lipu
 * @Date 2020/10/26 11:25
 * @Description
 */
@Repository
public interface IWareDao extends BaseMapper<Ware> {
}
