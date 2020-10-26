package com.bird.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bird.common.CommonResult;
import com.bird.dao.IDemandDao;
import com.bird.dao.IPurchaseDao;
import com.bird.entity.PageVo;
import com.bird.entity.user.Staff;
import com.bird.entity.ware.Demand;
import com.bird.entity.ware.Purchase;
import com.bird.feign.IStaffFeign;
import com.bird.service.IPurchaseService;
import com.bird.utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author lipu
 * @Date 2020/10/26 15:37
 * @Description
 */
@Service
@Transactional
public class PurchaseService implements IPurchaseService {
    @Resource
    private IPurchaseDao purchaseDao;
    @Resource
    private IStaffFeign staffFeign;
    @Resource
    private IDemandDao demandDao;

    /**
     * @Author lipu
     * @Date 2020/10/26 15:48
     * @Description 新建采购单
     */
    @Override
    public Integer add(Purchase purchase) {
        return purchaseDao.insert(purchase);
    }

    /**
     * @Author lipu
     * @Date 2020/10/26 16:03
     * @Description 查询所有采购单信息
     */
    @Override
    public List<Purchase> findAll(PageVo pageVo) {
        IPage<Purchase> purchaseIPage = new Page<>(pageVo.getPage(), pageVo.getSize());
        QueryWrapper<Purchase> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(pageVo.getKey() != null, (obj) -> {
            return obj.eq("id", pageVo.getKey());
        });
        List<Purchase> purchaseList = purchaseDao.selectPage(purchaseIPage, queryWrapper).getRecords();
        purchaseList.forEach((purchase -> {
            Staff staff = staffFeign.findById(purchase.getStaffId()).getData();
            purchase.setStaffName(staff.getUsername());
        }));
        return purchaseList;
    }

    /**
     * @Author lipu
     * @Date 2020/10/26 20:36
     * @Description 获取采购下拉列表
     */
    @Override
    public List<Purchase> selectList() {
        //只能获取状态未未分配和已分配的采购单
        QueryWrapper<Purchase> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("status",1).or().eq("status",2);
        List<Purchase> purchaseList = purchaseDao.selectList(queryWrapper);
        return purchaseList;
    }

    /**
     * @Author lipu
     * @Date 2020/10/26 20:45
     * @Description 合并采购需求
     */
    @Override
    public void mergePurchase(Purchase purchase) {
        List<Demand> demandList = purchase.getDemandList();
        //指定合并的采购单
        if (purchase.getId()!=null){
            demandList.forEach((demand -> {
                demand.setPurchaseId(purchase.getId());
                demand.setStatus(2);
                demandDao.update(demand,new QueryWrapper<Demand>().eq("purchase_id",purchase.getId()));
            }));

        }else {//未指定自动生成新单
            purchaseDao.insert(purchase);
            demandList.forEach(demand -> {
                demand.setPurchaseId(purchase.getId());
                demand.setStatus(2);
                demandDao.update(demand,new QueryWrapper<Demand>().eq("purchase_id",purchase.getId()));
            });
        }
    }
}
