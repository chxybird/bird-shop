package com.bird.utils;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @Author lipu
 * @Date 2020/10/11 16:15
 * @Description FastDFS工具类
 */
public class FastDFSUtils {
    /**
     * @Author lipu
     * @Date 2020/10/11 19:46
     * @Description 静态代码块加载Tracker信息
     */
    static {
        //使用spring提供的类来获取类路径下资源的真实路径
        String path = new ClassPathResource("fdfs.conf").getPath();
        try {
            //加载Tracker的配置信息
            ClientGlobal.init(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @Author lipu
     * @Date 2020/10/11 19:53
     * @Description 获取Tracker的信息
     */
    public static String getTracker() throws IOException {
        //创建Tracker客户端对象
        TrackerClient trackerClient=new TrackerClient();
        //通过Tracker对象获取连接信息
        TrackerServer connection = trackerClient.getConnection();
        //获取端口号
        int port = ClientGlobal.getG_tracker_http_port();
        //获取ip地址
        String ip = connection.getInetSocketAddress().getHostName();
        String url="http://"+ip+":"+port;
        return url;
    }

    /**
     * @Author lipu
     * @Date 2020/10/11 19:47
     * @Description 文件上传
     */
//    public static String[] uploadFile() throws Exception {
//        //创建Tracker客户端对象
//        TrackerClient trackerClient=new TrackerClient();
//        //通过Tracker对象获取连接信息
//        TrackerServer connection = trackerClient.getConnection();
//        //根据连接信息获取storage客户端对象
//        StorageClient storageClient = new StorageClient(connection, null);
//    }


}
