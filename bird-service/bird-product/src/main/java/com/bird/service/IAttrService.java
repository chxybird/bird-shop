package com.bird.service;

import com.bird.entity.PageVo;
import com.bird.entity.product.Attr;
import com.bird.entity.product.relation.TemplateAttr;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author lipu
 * @Date 2020/10/21 20:16
 * @Description
 */
public interface IAttrService {

    List<Attr> findByTemplateId(Long templateId, PageVo pageVo);

    List<Attr> findByTemplateIdWithout(Long templateId);

    List<Attr> findAll(PageVo pageVo, Integer type);

    void add(Attr attr, List<Long> idList);

    Integer deleteById(Long id);

    Integer update(Attr attr);

    Integer addTemplate(TemplateAttr templateAttr);

    Integer removeTemplate(TemplateAttr templateAttr);

}
