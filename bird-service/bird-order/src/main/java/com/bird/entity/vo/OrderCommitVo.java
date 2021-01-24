package com.bird.entity.vo;

import com.bird.entity.product.CartItem;
import lombok.Data;

import java.util.List;

/**
 * @Author lipu
 * @Date 2021/1/23 21:39
 * @Description
 */
@Data
public class OrderCommitVo {
    private Long staffId;
    private String username;
    private String address;
    private String phone;
    private String token;
    private String description;
    private List<CartItem> cartItemList;
}
