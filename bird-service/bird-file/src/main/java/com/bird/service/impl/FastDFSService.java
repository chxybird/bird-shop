package com.bird.service.impl;

import com.bird.common.CommonResult;
import com.bird.common.CommonStatus;
import com.bird.entity.FastDFSFile;
import com.bird.service.IFastDFSService;
import com.bird.utils.FastDFSUtils;
import lombok.extern.slf4j.Slf4j;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageServer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.Socket;

/**
 * @Author lipu
 * @Date 2020/10/13 16:12
 * @Description
 */
@Service
@Slf4j
public class FastDFSService implements IFastDFSService {

    /**
     * @Author lipu
     * @Date 2020/10/13 16:13
     * @Description 获取Tracker信息
     */
    @Override
    public CommonResult getTracker() {
        try {
            String url = FastDFSUtils.getTracker();
            return new CommonResult<String>(CommonStatus.SUCCESS, url);
        } catch (Exception e) {
            log.warn("未能连接到Tracker服务器");
            return new CommonResult<String>(CommonStatus.ERROR, "未能连接到Tracker服务器");
        }
    }

    /**
     * @Author lipu
     * @Date 2020/10/13 16:13
     * @Description 获取Storage信息
     */
    @Override
    public CommonResult getStorage() {
        try {
            StorageServer storage = FastDFSUtils.getStorage();
            int port = storage.getSocket().getPort();
            String ip = storage.getSocket().getInetAddress().getHostAddress();
            return new CommonResult<String>(CommonStatus.SUCCESS, ip + ":" + port);
        } catch (Exception e) {
            log.warn("Storage服务器连接失败");
            return new CommonResult<String>(CommonStatus.ERROR, "Storage服务器连接失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2020/10/13 16:13
     * @Description 文件上传
     */
    @Override
    public CommonResult upload(MultipartFile file) {
        try {
            String filename = file.getOriginalFilename();
            if (file.isEmpty()) {
                log.warn("上传的文件不能为空");
                return new CommonResult(CommonStatus.FILE_EMPTY, "上传的文件不能为空");
            }
            FastDFSFile fastDFSFile = new FastDFSFile();
            fastDFSFile.setContent(file.getBytes());
            fastDFSFile.setName(filename);
            fastDFSFile.setExt(filename.substring(filename.lastIndexOf(".") + 1));
            String filePath = FastDFSUtils.uploadFile(fastDFSFile);
            log.info("文件" + filename + "上传成功");
            return new CommonResult<String>(CommonStatus.SUCCESS, filePath);
        } catch (Exception e) {
            log.warn("FastDFS文件上传失败");
            return new CommonResult<String>(CommonStatus.ERROR, "FastDFS文件上传失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2020/10/13 16:14
     * @Description 文件下载 暂不实现
     */
    @Override
    public InputStream download(String groupName, String fileName) {
        return null;
    }

    /**
     * @Author lipu
     * @Date 2020/10/13 16:14
     * @Description 获取文件信息
     */
    @Override
    public CommonResult getFile(String groupName, String fileName) {
        try {
            FileInfo fileInfo = FastDFSUtils.getFile(groupName, fileName);
            if (fileInfo == null) {
                log.info("没有找到此文件");
                return new CommonResult<String>(CommonStatus.FILE_NOT_FOUND, "没有找到此文件");
            }
            return new CommonResult<FileInfo>(CommonStatus.SUCCESS, fileInfo);
        } catch (Exception e) {
            log.warn("获取文件失败");
            return new CommonResult<String>(CommonStatus.ERROR, "获取文件失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2020/10/13 16:14
     * @Description 删除文件
     */
    @Override
    public CommonResult deleteFile(String groupName, String fileName) {
        try {
            Integer result = FastDFSUtils.deleteFile(groupName, fileName);
            if (result == 0) {
                return new CommonResult(CommonStatus.SUCCESS, "文件删除成功");
            }else {
                return new CommonResult(CommonStatus.ERROR,"文件删除失败");
            }
        } catch (Exception e) {
            log.warn("FastDFS删除文件失败");
            return new CommonResult<String>(CommonStatus.ERROR, "FastDFS删除文件失败");
        }
    }
}
