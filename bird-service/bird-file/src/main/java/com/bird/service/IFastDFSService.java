package com.bird.service;

import com.bird.common.CommonResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @Author lipu
 * @Date 2020/10/13 16:06
 * @Description
 */
public interface IFastDFSService {

    CommonResult getTracker();

    CommonResult getStorage();

    CommonResult upload(MultipartFile file);

    InputStream download(String groupName, String fileName);

    CommonResult getFile(String groupName,String fileName) throws Exception;

    CommonResult deleteFile(String groupName, String fileName);

}
