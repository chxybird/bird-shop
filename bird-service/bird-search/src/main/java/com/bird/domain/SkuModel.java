package com.bird.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;

/**
 * @Author lipu
 * @Date 2020/11/1 18:44
 * @Description
 */
@Document(indexName = "product",type = "sku")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkuModel {
    @Id
    @Field(type = FieldType.Long, store = true)
    private Long id;
    @Field(type = FieldType.Text,store = true,analyzer = "ik_smart")
    private String name;
    @Field(type = FieldType.Long,store = true)
    private BigDecimal price;
    @Field(type = FieldType.Text,store = true,analyzer = "ik_smart")
    private String description;
    @Field(type = FieldType.Text,store = true,analyzer = "ik_smart")
    private String title;
    @Field(type = FieldType.Keyword,store = true)
    private String brandName;
    @Field(type = FieldType.Keyword,store = true)
    private String categoryName;
//    @Field(type = FieldType.Long,store = true)
//    private Long brandId;
//    @Field(type = FieldType.Long,store = true)
//    private Long categoryId;
}
