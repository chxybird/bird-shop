package com.bird.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lipu
 * @Date 2020/10/26 20:38
 * @Description
 */

@RestController
@RequestMapping("/demand")
@Api(tags = "采购需求模块接口")
@Slf4j
@Validated
public class DemandController {

}
