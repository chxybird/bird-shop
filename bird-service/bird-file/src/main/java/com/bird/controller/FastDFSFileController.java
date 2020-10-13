package com.bird.controller;

import com.bird.common.CommonResult;
import com.bird.service.IFastDFSService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * @Author lipu
 * @Date 2020/10/13 16:46
 * @Description
 */
@RestController
@RequestMapping("/fast")
@Api(tags = "FastDFS文件模块接口")
public class FastDFSFileController {
    @Resource
    private IFastDFSService fastDFSService;

    /**
     * @Author lipu
     * @Date 2020/10/13 16:49
     * @Description 获取文件信息
     */
    @GetMapping("/getFile")
    @ApiOperation("获取文件信息")
    public CommonResult getFile(
            @RequestParam("groupName")
            @NotNull(message = "小组名不能为空") String groupName,
            @RequestParam("fileName")
            @NotNull(message = "文件名不能为空") String fileName
    ) throws Exception {
       return fastDFSService.getFile(groupName,fileName);
    }

    /**
     * @Author lipu
     * @Date 2020/10/13 17:02
     * @Description 获取Tracker信息
     */
    @GetMapping("/getTracker")
    @ApiOperation("获取Tracker服务器信息")
    public CommonResult getTracker(){
        return fastDFSService.getTracker();
    }

    /**
     * @Author lipu
     * @Date 2020/10/13 17:51
     * @Description 获取Storage信息
     */
    @GetMapping("/getStorage")
    @ApiOperation("获取Storage服务器信息")
    public CommonResult getStorage(){
       return fastDFSService.getStorage();
    }

    /**
     * @Author lipu
     * @Date 2020/10/13 18:06
     * @Description 文件上传
     */
    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public CommonResult upload(@RequestParam("file") MultipartFile file){
       return fastDFSService.upload(file);
    }

    /**
     * @Author lipu
     * @Date 2020/10/13 18:57
     * @Description 删除文件
     */
    @DeleteMapping("/deleteFile")
    @ApiOperation("文件删除")
    public CommonResult deleteFile(
            @RequestParam("groupName")
            @NotNull(message = "小组名不能为空") String groupName,
            @RequestParam("fileName")
            @NotNull(message = "文件名不能为空") String fileName
    ){
        return fastDFSService.deleteFile(groupName,fileName);
    }
}
