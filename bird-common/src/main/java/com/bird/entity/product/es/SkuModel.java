package com.bird.entity.product.es;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author lipu
 * @Date 2020/11/1 18:44
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkuModel {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private String title;
    private String brandName;
    private String categoryName;
}
