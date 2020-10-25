package com.bird.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bird.entity.product.relation.SkuAttrValue;
import org.springframework.stereotype.Repository;

/**
 * @Author lipu
 * @Date 2020/10/23 15:00
 * @Description
 */
@Repository
public interface ISkuAttrValueDao extends BaseMapper<SkuAttrValue> {
}
