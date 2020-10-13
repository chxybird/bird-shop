package com.bird.service;

import com.bird.common.CommonResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

/**
 * @Author lipu
 * @Date 2020/10/13 9:20
 * @Description
 */
public interface IFileService {

    CommonResult upload(MultipartFile file) throws Exception;

    ResponseEntity download(String fileName) throws Exception;
}
