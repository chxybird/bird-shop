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
            List<Long> idList = new ArrayList<>();
            for (TemplateAttr templateAttr : templateAttrList) {
                idList.add(templateAttr.getAttrId());
            }
            List<Attr> attrList = attrDao.selectList(new QueryWrapper<Attr>().notIn("id", idList));
            return attrList;
        } else {
            List<Attr> attrList = attrDao.selectList(null);
            return attrList;
        }
    }

    /**
     * @Author lipu
     * @Date 2020/10/22 17:12
     * @Description (可根据模板id)查询所有属性信息 type 1基本属性 2销售属性 0查询所有
     */
    @Override
    public List<Attr> findAll(PageVo pageVo, Integer type) {
        IPage<Attr> attrIPage = new Page<>(pageVo.getPage(), pageVo.getSize());
        QueryWrapper<Attr> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(type != 0, "type", type);
        if (!StringUtils.isEmpty(pageVo.getKey())) {
            queryWrapper.and((obj -> {
                return obj.eq("id", pageVo.getKey()).or().like("name", pageVo.getKey());
            }));
        }
        List<Attr> attrList = attrDao.selectPage(attrIPage, queryWrapper).getRecords();
        return attrList;
    }

    /**
     * @Author lipu
     * @Date 2020/10/22 17:44
     * @Description 添加属性
     */
    @Override
    public void add(Attr attr, List<Long> idList) {
        attrDao.insert(attr);
        for (Long id : idList) {
            TemplateAttr templateAttr = new TemplateAttr();
            templateAttr.setAttrId(attr.getId());
            templateAttr.setTemplateId(id);
            templateAttrDao.insert(templateAttr);
        }
    }

    /**
     * @Author lipu
     * @Date 2020/10/22 19:56
     * @Description 删除属性信息
     */
    @Override
    public Integer deleteById(Long id) {
        Integer result = attrDao.deleteById(id);
        return result;
    }

    /**
     * @Author lipu
     * @Date 2020/10/22 20:39
     * @Description 更新属性信息
     */
    @Override
    public Integer update(Attr attr) {
        return attrDao.update(attr, new QueryWrapper<Attr>().eq("id", attr.getId()));
    }

    /**
     * @Author lipu
     * @Date 2020/10/22 20:48
     * @Description 添加属性模板关联
     */
    @Override
    public Integer addTemplate(TemplateAttr templateAttr) {
        return templateAttrDao.insert(templateAttr);
    }

    /**
     * @Author lipu
     * @Date 2020/10/22 20:48
     * @Description 删除属性模板关联
     */
    @Override
    public Integer removeTemplate(TemplateAttr templateAttr) {
        QueryWrapper<TemplateAttr> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attr_id", templateAttr.getAttrId())
                .eq("template_id", templateAttr.getTemplateId());
        Integer result = templateAttrDao.delete(queryWrapper);
        return result;
    }

    /**
     * @Author lipu
     * @Date 2020/10/23 10:55
     * @Description 根据模板id查询所有属性信息(商品添加使用)
     */
    @Override
    public List<Attr> findByTemplateIdWithType(Long templateId, Long type) {
        List<TemplateAttr> templateAttrList = templateAttrDao.selectList(
                new QueryWrapper<TemplateAttr>().eq("template_id", templateId));
        if (templateAttrList.size()>0){
            List<Long> idList=new ArrayList();
            for (TemplateAttr templateAttr:templateAttrList) {
                idList.add(templateAttr.getAttrId());
            }
            QueryWrapper<Attr> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("type",type).in("id",idList);
            List<Attr> attrList = attrDao.selectList(queryWrapper);
            return attrList;
        }
        return null;
    }
}
