package com.bird.service.impl;

import com.bird.common.CommonResult;
import com.bird.dao.IOrderDao;
import com.bird.dao.IOrderSkuDao;
import com.bird.entity.order.Order;
import com.bird.entity.order.relation.OrderSku;
import com.bird.entity.product.CartItem;
import com.bird.entity.product.Sku;
import com.bird.entity.vo.OrderCommitVo;
import com.bird.feign.IProductFeign;
import com.bird.service.IOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Author lipu
 * @Date 2021/1/23 22:04
 * @Description
 */
@Service
public class OrderService implements IOrderService {
    @Resource
    private IProductFeign skuFeign;
    @Resource
    private IOrderDao orderDao;
    @Resource
    private IOrderSkuDao orderSkuDao;

    /**
     * @Author lipu
     * @Date 2021/1/23 22:04
     * @Description 订单提交
     */
    @Override
    public void commit(OrderCommitVo orderCommitVo) {
        //接口幂等性校验 TODO

        //获取最新商品信息计算总价
        List<CartItem> cartItemList = orderCommitVo.getCartItemList();
        Map<Long, Integer> priceMapping = new HashMap<>(16);
        //记录商品数量映射关系
        cartItemList.forEach(cartItem -> {
            priceMapping.put(cartItem.getSkuId(), cartItem.getCount());
        });
        //获取最新商品信息
        List<Long> idList = orderCommitVo.getCartItemList().stream()
                .map(CartItem::getSkuId).collect(Collectors.toList());
        CommonResult commonResult = skuFeign.findBatch(idList);
        List<Sku> skuList = (List) commonResult.getData();
        //生成总价
        double totalPrice=0;
        for (Sku sku:skuList) {
            totalPrice =totalPrice+ priceMapping.get(sku.getId()) * sku.getPrice().doubleValue();
        }
        //清除选中的购物车相关数据 TODO 为了测试方便暂时不写

        //生成订单
        Order order = new Order();
        BeanUtils.copyProperties(orderCommitVo, order);
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setTotalPrice(new BigDecimal(totalPrice));
        orderDao.insert(order);
        //绑定订单与商品的信息
        skuList.stream().forEach((sku -> {
            OrderSku orderSku=new OrderSku();
            orderSku.setOrderId(order.getId());
            orderSku.setSkuId(sku.getId());
            orderSku.setPrice(sku.getPrice());
            orderSku.setCount(priceMapping.get(sku.getId()));
            orderSku.setTotalPrice(BigDecimal.valueOf(priceMapping.get(sku.getId()) * sku.getPrice().doubleValue()));
            orderSkuDao.insert(orderSku);
        }));
    }
}
