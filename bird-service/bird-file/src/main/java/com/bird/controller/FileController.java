package com.bird.controller;

import com.bird.common.CommonResult;
import com.bird.service.IFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;


/**
 * @Author lipu
 * @Date 2020/10/13 9:02
 * @Description
 */
@RestController
@RequestMapping("/file")
@Api(tags = "本地文件模块接口")
public class FileController {

    @Resource
    private IFileService fileService;

    /**
     * @Author lipu
     * @Date 2020/10/13 9:07
     * @Description 文件上传
     */
    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public CommonResult upload(@RequestParam("file") MultipartFile file) throws Exception{
        return fileService.upload(file);
    }

    /**
     * @Author lipu
     * @Date 2020/10/13 9:09
     * @Description 文件下载
     */
    @ApiOperation("文件下载")
    @GetMapping("/download")
    public ResponseEntity download(
            @NotNull(message = "文件名不能为空")
            @RequestParam("fileName") String fileName) throws Exception {
       return fileService.download(fileName);
    }
}
