package com.bird.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bird.dao.ICategoryTemplateDao;
import com.bird.dao.ITemplateAttrDao;
import com.bird.dao.ITemplateDao;
import com.bird.entity.PageVo;
import com.bird.entity.product.Template;
import com.bird.entity.product.relation.CategoryTemplate;
import com.bird.entity.product.relation.TemplateAttr;
import com.bird.service.ITemplateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author lipu
 * @Date 2020/10/15 9:35
 * @Description
 */
@Service
@Transactional
public class TemplateService implements ITemplateService {
    @Resource
    private ITemplateDao templateDao;
    @Resource
    private ICategoryTemplateDao categoryTemplateDao;
    @Resource
    private ITemplateAttrDao templateAttrDao;

    /**
     * @Author lipu
     * @Date 2020/10/15 9:45
     * @Description 根据分类id查询模板信息
     */
    @Override
    public List<Template> findByCategoryId(Long categoryId) {
        List<CategoryTemplate> categoryTemplateList = categoryTemplateDao.selectList(
                new QueryWrapper<CategoryTemplate>().eq("category_id", categoryId)
        );
        List<Long> idList = new ArrayList<>();
        for (CategoryTemplate categoryTemplate : categoryTemplateList) {
            idList.add(categoryTemplate.getTemplateId());
        }
        List<Template> templateList = templateDao.selectList(
                new QueryWrapper<Template>().in("id", idList)
        );
        return templateList;
    }

    /**
     * @Author lipu
     * @Date 2020/10/20 19:43
     * @Description 查询所有模板信息
     */
    @Override
    public List<Template> findAll(PageVo pageVo) {
        IPage<Template> templateIPage = new Page<>(pageVo.getPage(), pageVo.getSize());
        QueryWrapper<Template> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(pageVo.getKey())) {
            queryWrapper.eq("name", pageVo.getKey());
        }
        List<Template> templateList = templateDao.selectPage(templateIPage, queryWrapper).getRecords();
        return templateList;
    }

    /**
     * @Author lipu
     * @Date 2020/10/15 10:15
     * @Description 添加模板
     */
    @Override
    public Integer add(Template template) {
        int result = templateDao.insert(template);
        return result;
    }

    /**
     * @Author lipu
     * @Date 2020/10/15 10:22
     * @Description 根据id删除模板
     */
    @Override
    public Integer deleteById(Long id) {
        return templateDao.deleteById(id);
    }

    /**
     * @Author lipu
     * @Date 2020/10/15 10:22
     * @Description 更新模板信息
     */
    @Override
    public Integer update(Template template) {
        UpdateWrapper<Template> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", template.getId());
        return templateDao.update(template, updateWrapper);
    }

    /**
     * @Author lipu
     * @Date 2020/10/21 19:55
     * @Description 添加模板分类关联
     */
    @Override
    public Integer addToCategory(CategoryTemplate categoryTemplate) {
        Integer result = categoryTemplateDao.insert(categoryTemplate);
        return result;
    }

    /**
     * @Author lipu
     * @Date 2020/10/21 19:55
     * @Description 删除模板分类关联
     */
    @Override
    public Integer removeCategory(CategoryTemplate categoryTemplate) {
        QueryWrapper<CategoryTemplate> queryWrapper = new QueryWrapper();
        queryWrapper.eq("template_id", categoryTemplate.getTemplateId())
                .eq("category_id", categoryTemplate.getCategoryId());
        Integer result = categoryTemplateDao.delete(queryWrapper);
        return result;
    }

    /**
     * @Author lipu
     * @Date 2020/10/21 21:56
     * @Description 添加模板属性关联
     */
    @Override
    public Integer addToAttr(TemplateAttr templateAttr) {
        Integer result = templateAttrDao.insert(templateAttr);
        return result;
    }

    /**
     * @Author lipu
     * @Date 2020/10/21 22:00
     * @Description 删除模板属性关联
     */
    @Override
    public Integer removeAttr(TemplateAttr templateAttr) {
        QueryWrapper<TemplateAttr> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("template_id", templateAttr.getTemplateId())
                .eq("attr_id", templateAttr.getAttrId());
        Integer result = templateAttrDao.delete(queryWrapper);
        return result;
    }

    /**
     * @Author lipu
     * @Date 2020/10/22 20:16
     * @Description 根据属性查询已关联的模板信息
     */
    @Override
    public List<Template> findByAttrId(Long attrId, PageVo pageVo) {
        IPage<Template> templateIPage = new Page<>(pageVo.getPage(),pageVo.getSize());
        List<TemplateAttr> templateAttrList = templateAttrDao.selectList(
                new QueryWrapper<TemplateAttr>().eq("attr_id", attrId));
        if (templateAttrList.size()>0){
            List<Long> idList=new ArrayList<>();
            for (TemplateAttr templateAttr:templateAttrList) {
                idList.add(templateAttr.getTemplateId());
            }
            QueryWrapper<Template> queryWrapper=new QueryWrapper<>();
            queryWrapper.in("id",idList);
            if (!StringUtils.isEmpty(pageVo.getKey())){
                queryWrapper.and((obj)->{
                    return obj.eq("id",pageVo.getKey()).or().like("name",pageVo.getKey());
                });
            }
            List<Template> templateList = templateDao.selectPage(templateIPage, queryWrapper).getRecords();
            return templateList;
        }
        return null;
    }

    /**
     * @Author lipu
     * @Date 2020/10/22 20:16
     * @Description 根据属性查询未关联的模板信息
     */
    @Override
    public List<Template> findByAttrIdWithout(Long attrId) {
        List<TemplateAttr> templateAttrList = templateAttrDao.selectList(
                new QueryWrapper<TemplateAttr>().eq("attr_id", attrId));
        if (templateAttrList.size()>0){
            List<Long> idList=new ArrayList<>();
            for (TemplateAttr templateAttr:templateAttrList) {
                idList.add(templateAttr.getTemplateId());
            }
            QueryWrapper<Template> queryWrapper=new QueryWrapper<>();
            queryWrapper.notIn("id",idList);
            List<Template> templateList = templateDao.selectList(queryWrapper);
            return templateList;
        }
        return templateDao.selectList(null);
    }
}
