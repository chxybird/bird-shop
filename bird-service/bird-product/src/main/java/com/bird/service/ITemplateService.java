package com.bird.service;

import com.bird.entity.PageVo;
import com.bird.entity.product.Template;

import java.util.List;

/**
 * @Author lipu
 * @Date 2020/10/15 9:34
 * @Description
 */
public interface ITemplateService {

    List<Template> findByCategoryId(Long categoryId);

    Integer add(Template template);

    Integer deleteById(Long id);

    Integer update(Template template);


}
