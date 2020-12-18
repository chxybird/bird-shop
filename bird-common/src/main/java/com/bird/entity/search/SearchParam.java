package com.bird.entity.search;

import lombok.Data;

/**
 * @Author lipu
 * @Date 2020/11/7 19:38
 * @Description 检索VO类
 */
@Data
public class SearchParam {
    //商品名称检索关键字
    private String keyword;
    //品牌
    private String brandName;
    //分类
    private String categoryName;
    //价格区间
    private Integer minPrice;
    private Integer maxPrice;
    //排序条件
    private String sort;
    //分页条件
    private Integer page;
    private Integer size;
}
