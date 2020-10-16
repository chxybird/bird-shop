//package com.bird.entity;
//
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.annotation.TableId;
//import com.baomidou.mybatisplus.annotation.TableName;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//
//import java.util.Date;
//
///**
// * @Author lipu
// * @Date 2020/10/14 20:22
// * @Description
// */
//@Data
//@TableName("t_sku")
//@ApiModel(value="商品实体类")
//public class Sku {
//    @TableId(value = "id", type = IdType.AUTO)
//    @ApiModelProperty(value = "商品id")
//    private Long id;
//    @ApiModelProperty(value = "商品名")
//    private String name;
//    @ApiModelProperty(value = "描述")
//    private String description;
//    @ApiModelProperty(value = "图片地址")
//    private String img;
//    @ApiModelProperty(value = "标题")
//    private String title;
//    @ApiModelProperty(value = "价格")
//    private Integer price;
//    @ApiModelProperty(value = "售卖数量")
//    private Integer saleCount;
//    @ApiModelProperty(value = "创建时间")
//    private Date gmtCreate;
//    @ApiModelProperty(value = "更新时间")
//    private Date gmtModified;
//    @ApiModelProperty(value = "所属抽象商品类")
//    private Long spuId;
//}
