package com.bird.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bird.entity.ware.relation.WareSku;
import org.springframework.stereotype.Repository;

/**
 * @Author lipu
 * @Date 2020/10/31 14:30
 * @Description
 */
@Repository
public interface IWareSkuDao extends BaseMapper<WareSku> {
}
