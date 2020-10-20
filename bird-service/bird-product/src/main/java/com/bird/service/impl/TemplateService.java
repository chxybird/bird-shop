package com.bird.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bird.dao.ICategoryTemplateDao;
import com.bird.dao.ITemplateDao;
import com.bird.entity.PageVo;
import com.bird.entity.product.Template;
import com.bird.entity.product.relation.CategoryTemplate;
import com.bird.service.ITemplateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
        List<Long> idList=new ArrayList<>();
        for (CategoryTemplate categoryTemplate: categoryTemplateList) {
            idList.add(categoryTemplate.getTemplateId());
        }
        List<Template> templateList = templateDao.selectBatchIds(idList);
        return templateList;
    }

    /**
     * @Author lipu
     * @Date 2020/10/15 10:15
     * @Description 添加模板
     */
    @Override
    public Integer add(Template template){
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
        UpdateWrapper<Template> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("id",template.getId());
        return templateDao.update(template,updateWrapper);
    }
}
