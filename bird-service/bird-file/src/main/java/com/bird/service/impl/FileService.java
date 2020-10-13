package com.bird.service.impl;

import com.bird.common.CommonResult;
import com.bird.common.CommonStatus;
import com.bird.service.IFileService;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.build.Plugin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.UUID;

/**
 * @Author lipu
 * @Date 2020/10/13 9:25
 * @Description
 */
@Service
@Slf4j
public class FileService implements IFileService {

    //本地文件路径
    @Value("${local.file.path}")
    private String filePath;

    /**
     * @Author lipu
     * @Date 2020/10/13 9:26
     * @Description 文件上传
     */
    @Override
    public CommonResult upload(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                log.warn("上传的文件不能为空");
                return new CommonResult(CommonStatus.FILE_EMPTY, "上传的文件不能为空");
            }
            //组装文件名(唯一性组装)
            String fileName = file.getOriginalFilename();
            UUID randomUUID = UUID.randomUUID();
            String uuid = randomUUID.toString().replaceAll("-", "");
            String finalFileName = uuid + "-" + fileName;
            //构建文件目录
            File contents = new File(filePath);
            //如果目录存在创建目录
            if (!contents.exists()) {
                contents.mkdir();
                log.info("初始化文件上传目录");
            }
            //构建文件
            File f = new File(contents, finalFileName);
            //将文件输出到磁盘
            FileOutputStream outputStream = new FileOutputStream(f);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            bufferedOutputStream.write(file.getBytes());
            bufferedOutputStream.close();
            outputStream.close();
            return new CommonResult(CommonStatus.SUCCESS, finalFileName);
        } catch (Exception e) {
            log.error("发生异常,文件上传失败");
            return new CommonResult(CommonStatus.ERROR, "发生异常,文件上传失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2020/10/13 9:26
     * @Description 文件下载
     */
    @Override
    public ResponseEntity download(String fileName) {
        try {
            //构建文件
            File file = new File(filePath, fileName);
            if (!file.exists()) {
                log.warn("文件不存在");
                CommonResult commonResult = new CommonResult(CommonStatus.FILE_NOT_FOUND, "没有找到文件");
                return ResponseEntity.status(HttpStatus.OK).body("文件不存在");
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Disposition","attachment;filename="+new String(fileName.getBytes("UTF-8"),"ISO8859-1"));
            return ResponseEntity.ok().headers(httpHeaders).contentLength(file.length())
                    .contentType(MediaType.parseMediaType("application/octet-stream")).body(new InputStreamResource(fileInputStream));
        } catch (Exception e) {
            log.error("发生异常,文件下载失败");
            CommonResult commonResult = new CommonResult(CommonStatus.ERROR, "文件下载失败");
            return ResponseEntity.status(HttpStatus.OK).body(commonResult);
        }
    }
}
