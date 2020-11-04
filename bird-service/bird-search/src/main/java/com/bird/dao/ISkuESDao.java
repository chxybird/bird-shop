package com.bird.dao;

import com.bird.domain.SkuModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author lipu
 * @Date 2020/11/1 19:22
 * @Description
 */
@Repository
public interface ISkuESDao extends ElasticsearchRepository<SkuModel,Long> {

}
