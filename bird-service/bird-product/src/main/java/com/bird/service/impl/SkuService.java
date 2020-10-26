package com.bird.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bird.dao.ISkuDao;
import com.bird.entity.PageVo;
import com.bird.entity.product.Sku;
import com.bird.service.ISkuService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author lipu
 * @Date 2020/10/26 9:24
 * @Description
 */
@Service
public class SkuService implements ISkuService {
    @Resource
    private ISkuDao skuDao;

    /**
     * @Author lipu
     * @Date 2020/10/26 10:05
     * @Description 查询所有商品信息
     */
    @Override
    public List<Sku> findAll(PageVo pageVo) {
        IPage<Sku> skuIPage=new Page<>(pageVo.getPage(),pageVo.getSize());
        QueryWrapper<Sku> queryWrapper=new QueryWrapper<>();
        if (!StringUtils.isEmpty(pageVo.getKey())){
            queryWrapper.and((obj)->{
                return obj.eq("id",pageVo.getKey()).or().like("name",pageVo.getKey());
            });
        }
        return skuDao.selectPage(skuIPage,queryWrapper).getRecords();
    }
}
