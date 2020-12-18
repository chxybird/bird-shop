package com.bird.domain;

import com.bird.entity.product.Sku;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author lipu
 * @Date 2020/11/7 20:04
 * @Description
 */
@Data
public class SearchResult {
    //检索基本数据
    private List<SkuModel> skuModelList;
    //总记录数
    private Integer TotalNum;
    //总页数
    private Integer totalPageNum;

}
