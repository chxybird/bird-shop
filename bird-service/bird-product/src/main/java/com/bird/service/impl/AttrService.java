package com.bird.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bird.dao.IAttrDao;
import com.bird.dao.ITemplateAttrDao;
import com.bird.entity.PageVo;
import com.bird.entity.product.Attr;
import com.bird.entity.product.relation.TemplateAttr;
import com.bird.service.IAttrService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author lipu
 * @Date 2020/10/21 20:18
 * @Description
 */
@Service
@Transactional
public class AttrService implements IAttrService {
    @Resource
    private IAttrDao attrDao;
    @Resource
    private ITemplateAttrDao templateAttrDao;

    /**
     * @Author lipu
     * @Date 2020/10/21 20:19
     * @Description 根据模板id查询属信息
     */
    @Override
    public List<Attr> findByTemplateId(Long templateId, PageVo pageVo) {
        IPage<Attr> attrIPage = new Page<>(pageVo.getPage(), pageVo.getSize());
        List<TemplateAttr> templateAttrList = templateAttrDao.selectList(
                new QueryWrapper<TemplateAttr>().eq("template_id", templateId));
        if (templateAttrList.size() > 0) {
            List<Long> idList = new ArrayList<>();
            for (TemplateAttr templateAttr : templateAttrList) {
                idList.add(templateAttr.getAttrId());
            }
            QueryWrapper<Attr> queryWrapper = new QueryWrapper<>();
            queryWrapper.in("id", idList);
            if (!StringUtils.isEmpty(pageVo.getKey())) {
                queryWrapper.and((obj) -> {
                    return obj.eq("id", pageVo.getKey()).or().like("name", pageVo.getKey());
                });
            }
            List<Attr> attrList = attrDao.selectPage(attrIPage, queryWrapper).getRecords();
            return attrList;
        }
        return null;
    }

    /**
     * @Author lipu
     * @Date 2020/10/21 21:40
     * @Description 根据模板id查询属信息(without)
     */
    @Override
    public List<Attr> findByTemplateIdWithout(Long templateId) {
        List<TemplateAttr> templateAttrList = templateAttrDao.selectList(
                new QueryWrapper<TemplateAttr>().eq("template_id", templateId));
        if (templateAttrList.size() > 0) {
            List<Long> idList=new ArrayList<>();
            for (TemplateAttr templateAttr:templateAttrList) {
                idList.add(templateAttr.getAttrId());
            }
            List<Attr> attrList = attrDao.selectList(new QueryWrapper<Attr>().notIn("id", idList));
            return attrList;
        }else {
            List<Attr> attrList = attrDao.selectList(null);
            return attrList;
        }
    }
}
