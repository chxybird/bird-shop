package com.bird.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bird.dao.IDemandDao;
import com.bird.dao.IPurchaseDao;
import com.bird.entity.ware.Demand;
import com.bird.entity.ware.Purchase;
import com.bird.service.IDemandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author lipu
 * @Date 2020/10/26 15:43
 * @Description
 */
@Service
@Transactional
public class DemandService implements IDemandService {
    @Resource
    private IDemandDao demandDao;
    @Resource
    private IPurchaseDao purchaseDao;

    /**
     * @Author lipu
     * @Date 2020/10/27 10:27
     * @Description 合并采购需求到采购单
     */
    @Override
    public void mergeToPurchase(Purchase purchase) {
        List<Demand> demandList = purchase.getDemandList();
        //指定合并的采购单
        if (purchase.getId()!=null){
            demandList.forEach((demand -> {
                demand.setPurchaseId(purchase.getId());
                demand.setStatus(2);
                demandDao.update(demand,
                        new QueryWrapper<Demand>().eq("purchase_id",purchase.getId()));
            }));

        }else {//未指定自动生成新单
            purchaseDao.insert(purchase);
            demandList.forEach(demand -> {
                demand.setPurchaseId(purchase.getId());
                demand.setStatus(2);
                demandDao.update(demand,
                        new QueryWrapper<Demand>().eq("purchase_id",purchase.getId()));
            });
        }
    }

    /**
     * @Author lipu
     * @Date 2020/10/31 13:16
     * @Description 新增采购需求
     */
    @Override
    public Integer add(Demand demand) {
        return demandDao.insert(demand);
    }
}
