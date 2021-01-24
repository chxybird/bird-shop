package com.bird.controller;

import com.bird.common.CommonResult;
import com.bird.common.CommonStatus;
import com.bird.entity.vo.OrderCommitVo;
import com.bird.service.IOrderService;
import com.bird.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author lipu
 * @Date 2020/10/14 9:32
 * @Description
 */
@RestController
@RequestMapping("/order")
@Api(tags = "订单模块")
public class OrderController {
    @Resource
    private IOrderService orderService;

    /**
     * @Author lipu
     * @Date 2021/1/23 21:37
     * @Description 订单提交
     */
    @PostMapping("/commit")
    @ApiOperation("提交订单")
    public CommonResult commit(@RequestHeader("Authorization")String token,@RequestBody OrderCommitVo orderCommitVo){
        Long staffId = JwtUtils.getStaffInfo(token);
        orderCommitVo.setStaffId(staffId);
        orderService.commit(orderCommitVo);
        return new CommonResult(CommonStatus.SUCCESS,"提交成功");
    }
}
