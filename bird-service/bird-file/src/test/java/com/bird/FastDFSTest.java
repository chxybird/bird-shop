package com.bird;

import com.bird.entity.FastDFSFile;
import com.bird.utils.FastDFSUtils;
import org.csource.fastdfs.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

/**
 * @Author lipu
 * @Date 2020/10/11 19:56
 * @Description
 */
@SpringBootTest(classes = FileApp.class)
@RunWith(SpringRunner.class)
public class FastDFSTest {
    /**
     * @Author lipu
     * @Date 2020/10/11 20:20
     * @Description 获取tracker服务器信息
     */
    @Test
    public void getTracker() throws IOException {
        String tracker = FastDFSUtils.getTracker();
        System.out.println(tracker);
    }

    /**
     * @Author lipu
     * @Date 2020/10/12 17:01
     * @Description 获取Storage服务器信息
     */
    @Test
    public void getStorage() throws Exception {
        StorageServer storage = FastDFSUtils.getStorage();
        System.out.println(storage);
    }

    /**
     * @Author lipu
     * @Date 2020/10/12 17:59
     * @Description 文件上传
     */
    @Test
    public void uploadFile() throws Exception {
        String path = new ClassPathResource("fdfs.conf").getPath();
        ClientGlobal.init(path);
        TrackerClient trackerClient=new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        String[] info = storageClient.upload_file("F:\\Data\\bird.jpg", "jpg",null);
        for (String s:info) {
            System.out.println(s);
        }
    }
}
