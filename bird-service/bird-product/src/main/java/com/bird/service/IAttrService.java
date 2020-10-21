package com.bird.service;

import com.bird.entity.PageVo;
import com.bird.entity.product.Attr;
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
}
