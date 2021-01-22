package com.bird.controller;

import com.bird.common.CommonResult;
import com.bird.common.CommonStatus;
import com.bird.entity.user.Address;
import com.bird.service.IAddressService;
import com.bird.utils.JwtUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author lipu
 * @Date 2021/1/22 13:35
 * @Description
 */
@RestController
@RequestMapping("/address")
public class AddressController {
    @Resource
    private IAddressService addressService;

    /**
     * @Author lipu
     * @Date 2021/1/22 13:36
     * @Description 添加收获地址
     */
    @ApiOperation("添加收货地址")
    @PostMapping("/add")
    public CommonResult add(@RequestBody Address address,@RequestHeader("Authorization")String token){
        //获取用户登录信息
        Long staffId = JwtUtils.getStaffInfo(token);
        address.setStaffId(staffId);
        addressService.add(address);
        return new CommonResult(CommonStatus.SUCCESS,"添加成功");
    }

    /**
     * @Author lipu
     * @Date 2021/1/22 13:50
     * @Description 删除收货地址
     */
    @DeleteMapping("/delete")
    @ApiOperation("删除收货地址")
    public CommonResult delete(@RequestParam("id") Long id){
        addressService.delete(id);
        return new CommonResult(CommonStatus.SUCCESS,"删除成功");
    }

    /**
     * @Author lipu
     * @Date 2021/1/22 13:50
     * @Description 查看收货地址
     */
    @GetMapping("/findByStaffId")
    @ApiOperation("查看收货地址")
    public CommonResult findByStaffId(@RequestHeader("Authorization")String token){
        //获取用户登录信息
        Long staffId = JwtUtils.getStaffInfo(token);
        List<Address> addressList = addressService.findByStaffId(staffId);
        return new CommonResult(CommonStatus.SUCCESS,addressList);

    }
}
