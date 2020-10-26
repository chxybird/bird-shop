package com.bird.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bird.dao.IWareDao;
import com.bird.entity.PageVo;
import com.bird.entity.ware.Ware;
import com.bird.service.IWareService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author lipu
 * @Date 2020/10/26 11:24
 * @Description
 */
@Service
@Transactional
public class WareService implements IWareService {

    @Resource
    private IWareDao wareDao;

    /**
     * @Author lipu
     * @Date 2020/10/26 11:25
     * @Description 添加仓库信息
     */
    @Override
    public Integer add(Ware ware) {
        return wareDao.insert(ware);
    }

    /**
     * @Author lipu
     * @Date 2020/10/26 14:07
     * @Description 查询仓库信息
     */
    @Override
    public List<Ware> findAll(PageVo pageVo) {
        IPage<Ware> wareIPage=new Page<>(pageVo.getPage(),pageVo.getSize());
        QueryWrapper<Ware> queryWrapper=new QueryWrapper<>();
        queryWrapper.and(pageVo.getKey()!=null,(obj)->{
            return obj.eq("id",pageVo.getKey()).or().like("name",pageVo.getKey());
        });
        List<Ware> wareList = wareDao.selectPage(wareIPage, queryWrapper).getRecords();

        return wareList;
    }

    /**
     * @Author lipu
     * @Date 2020/10/26 14:21
     * @Description 获取仓库下拉列表
     */
    @Override
    public List<Ware> selectList() {
        List<Ware> wareList = wareDao.selectList(null);
        return wareList;
    }
}
