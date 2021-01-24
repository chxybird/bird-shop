package com.bird.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bird.dao.ICartDao;
import com.bird.dao.ICartItemDao;
import com.bird.entity.product.Cart;
import com.bird.entity.product.CartItem;
import com.bird.service.ICartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author lipu
 * @Date 2020/12/24 11:31
 * @Description
 */
@Service
public class CartService implements ICartService {
    @Resource
    private ICartDao cartDao;
    @Resource
    private ICartItemDao cartItemDao;

    /**
     * @Author lipu
     * @Date 2020/12/24 11:37
     * @Description 添加购物车
     */
    @Override
    public void add(Cart cart) {
        //获取当前用户id获取购物车id信息
        Long staffId = cart.getStaffId();
        Long cartId = cartDao.selectOne(new QueryWrapper<Cart>().eq("staff_id", staffId)).getId();
        //获取购物项目
        List<CartItem> cartItemList = cart.getCartItemList();
        //设置购物车、购物项绑定关系
        for (CartItem cartItem:cartItemList) {
            cartItem.setCartId(cartId);
            cartItemDao.insert(cartItem);
        }
    }

    /**
     * @Author lipu
     * @Date 2021/1/21 17:04
     * @Description 根据用户id查询购物车信息
     */
    @Override
    public Cart findByStaffId(Long staffId) {
        Cart cart = cartDao.selectOne(new QueryWrapper<Cart>()
                .eq("staff_id",staffId));
        //查询购物项
        List<CartItem> cartItemList = cartItemDao.selectList(new QueryWrapper<CartItem>()
                .eq("cart_id", cart.getId()));
        cart.setCartItemList(cartItemList);
        return cart;
    }

    /**
     * @Author lipu
     * @Date 2021/1/21 17:35
     * @Description 初始化购物车信息
     */
    @Override
    public Boolean init(Long staffId) {
        Cart cart=new Cart();
        cart.setStaffId(staffId);
        cartDao.insert(cart);
        return true;
    }

    /**
     * @Author lipu
     * @Date 2021/1/21 18:55
     * @Description 清除购物车
     */
    @Override
    public void delete(Long staffId) {
        //根据用户id查找购物车id
        Cart cart = cartDao.selectOne(new QueryWrapper<Cart>().eq("staff_id", staffId));
        //清空购物项目
        cartItemDao.delete(new QueryWrapper<CartItem>().eq("cart_id",cart.getId()));
    }

    /**
     * @Author qgr
     * @Date 2021/1/22 15:37
     * @Description 更新购物车信息
     */
    @Override
    public void update(Cart cart) {

    }
}
