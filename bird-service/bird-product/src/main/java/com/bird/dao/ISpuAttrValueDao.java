package com.bird.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bird.entity.product.relation.SpuAttrValue;
import org.springframework.stereotype.Repository;

/**
 * @Author lipu
 * @Date 2020/10/23 14:59
 * @Description
 */
@Repository
public interface ISpuAttrValueDao extends BaseMapper<SpuAttrValue> {
}
