package com.bird.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bird.dao.IDemandDao;
import com.bird.dao.IPurchaseDao;
import com.bird.dao.IWareSkuDao;
import com.bird.entity.PageVo;
import com.bird.entity.product.Sku;
import com.bird.entity.user.Staff;
import com.bird.entity.ware.Demand;
import com.bird.entity.ware.Enum.DemandStatus;
import com.bird.entity.ware.Enum.PurchaseStatus;
import com.bird.entity.ware.Purchase;
import com.bird.entity.ware.relation.WareSku;
import com.bird.feign.ISkuFeign;
import com.bird.feign.IStaffFeign;
import com.bird.service.IPurchaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
    @Resource
    private IWareSkuDao wareSkuDao;
    @Resource
    private ISkuFeign skuFeign;



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
            //设置当前采购单的所有采购需求
            List<Demand> demandList = demandDao.selectList(
                    new QueryWrapper<Demand>().eq("purchase_id", purchase.getId()));
            purchase.setDemandList(demandList);
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
        QueryWrapper<Purchase> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1).or().eq("status", 2);
        List<Purchase> purchaseList = purchaseDao.selectList(queryWrapper);
        return purchaseList;
    }

    /**
     * @Author lipu
     * @Date 2020/10/27 10:35
     * @Description 分配采购单给采购人员
     */
    @Override
    public Integer distribute(Long staffId, Long id) {
        Purchase purchase = new Purchase();
        purchase.setStaffId(staffId);
        return purchaseDao.update(purchase, new QueryWrapper<Purchase>().eq("id", id));
    }

    /**
     * @Author lipu
     * @Date 2020/10/27 10:49
     * @Description 根据采购人员id查询采购单信息
     */
    @Override
    public List<Purchase> findByStaffId(Long staffId) {
        List<Purchase> purchaseList = purchaseDao.selectList(
                new QueryWrapper<Purchase>().eq("staff_id", staffId));
        return purchaseList;
    }

    /**
     * @Author lipu
     * @Date 2020/10/31 13:26
     * @Description 领取采购单
     */
    @Override
    public void accept(Long id) {
        //改变采购单的状态为已领取 3
        Purchase purchase = purchaseDao.selectOne(
                new QueryWrapper<Purchase>().eq("id", id));
        purchase.setStatus(3);
        purchaseDao.update(purchase,
                new QueryWrapper<Purchase>().eq("id",id));

        //改变采购需求的状态为正在采购 2
        List<Demand> demandList = demandDao.selectList(
                new QueryWrapper<Demand>().eq("purchase_id", id));
        demandList.forEach((demand -> {
            demand.setStatus(2);
            demandDao.update(demand,
                    new QueryWrapper<Demand>().eq("id",demand.getId()));
        }));
    }

    /**
     * @Author lipu
     * @Date 2020/10/31 13:47
     * @Description 完成采购
     */
    @Override
    public void finish(Purchase purchase) {
        List<Demand> demandList = purchase.getDemandList();
        //设置采购单状态
        for (Demand demand:demandList) {
            if (demand.getStatus()== DemandStatus.FINISH_FAIL.getStatus()){
                purchase.setStatus(PurchaseStatus.FINISH_FAIL.getStatus());
            }else {
                purchase.setStatus(PurchaseStatus.FINISHED.getStatus());
            }
        }
        purchaseDao.update(purchase,
                new QueryWrapper<Purchase>().eq("id",purchase.getId()));
        //设置每个采购需求状态
        for (Demand demand:demandList) {
            demandDao.update(demand,
                    new QueryWrapper<Demand>().eq("id",demand.getId()));
        }
        //库存更新
        for (Demand demand:demandList) {
            if (demand.getStatus()!=DemandStatus.FINISH_FAIL.getStatus()){
                WareSku wareSku = wareSkuDao.selectOne(
                        new QueryWrapper<WareSku>().eq("ware_id", demand.getWareId())
                                .eq("sku_id", demand.getSkuId()));
                //如果没有库存记录新增
                if (wareSku!=null){
                    Sku sku = skuFeign.findById(demand.getSkuId()).getData();
                    WareSku data=new WareSku();
                    wareSku.setSkuName(sku.getName());
                    wareSku.setSkuId(demand.getSkuId());
                    wareSku.setStock(demand.getNum());
                    wareSku.setWareId(demand.getWareId());
                    wareSkuDao.insert(data);
                }else {//如果有更新操作
                    wareSku.setStock(wareSku.getStock()+demand.getNum());
                    wareSkuDao.update(wareSku,
                            new QueryWrapper<WareSku>().eq("id",wareSku.getId()));
                }
            }
        }
    }
}
