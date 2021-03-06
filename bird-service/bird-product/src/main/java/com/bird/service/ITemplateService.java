package com.bird.service;

import com.bird.entity.PageVo;
import com.bird.entity.product.Template;
import com.bird.entity.product.relation.CategoryTemplate;
import com.bird.entity.product.relation.TemplateAttr;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author lipu
 * @Date 2020/10/15 9:34
 * @Description
 */
public interface ITemplateService {

    List<Template> findByCategoryId(Long categoryId);

    List<Template> findAll(PageVo pageVo);

    Integer add(Template template);

    Integer deleteById(Long id);

    Integer update(Template template);

    Integer addToCategory(CategoryTemplate categoryTemplate);

    Integer removeCategory(CategoryTemplate categoryTemplate);

    Integer addToAttr(TemplateAttr templateAttr);

    Integer removeAttr(TemplateAttr templateAttr);

    List<Template> findByAttrId(Long attrId, PageVo pageVo);

    List<Template> findByAttrIdWithout(Long attrId);


}
