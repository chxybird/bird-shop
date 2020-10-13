package com.bird.utils;

import com.bird.entity.FastDFSFile;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

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
    public static String getTracker() throws Exception {
        //创建Tracker客户端对象
        TrackerClient trackerClient=new TrackerClient();
        //获取tracker服务器对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //获取端口号
        int port = ClientGlobal.getG_tracker_http_port();
        //获取ip地址
        String ip = trackerServer.getInetSocketAddress().getHostName();
        String url="http://"+ip+":"+port;
        return url;
    }

    /**
     * @Author lipu
     * @Date 2020/10/12 16:40
     * @Description 获取Storage的信息
     */
    public static StorageServer getStorage() throws Exception {
        //创建Tracker客户端对象
        TrackerClient trackerClient=new TrackerClient();
        //通过Tracker对象获取连接信息
        TrackerServer trackerServer = trackerClient.getConnection();
        //获取Storage信息
        StorageServer storage = trackerClient.getStoreStorage(trackerServer);
        return storage;
    }


    /**
     * @Author lipu
     * @Date 2020/10/11 19:47
     * @Description 文件上传
     */
    public static String uploadFile(FastDFSFile file) throws Exception {
        //创建Tracker客户端对象
        TrackerClient trackerClient=new TrackerClient();
        //通过Tracker对象获取连接信息
        TrackerServer trackerServer = trackerClient.getConnection();
        //获取一个Storage的信息
        StorageServer storage = trackerClient.getStoreStorage(trackerServer);
        //根据连接信息获取storage客户端对象
        StorageClient storageClient = new StorageClient(trackerServer, storage);
        //上传文件
        //参数一 上传文件的内容字节数组 参数二文件扩展名 参数三附加信息
        //返回值 返回一个数组包含两个参数info[0]、info[1]。
        //info[0]表示Storage的组名字 例如group1
        //info[1]表示存储在Storage上的文件名 例如M00/11/22/bird.jpg
        String[] info = storageClient.upload_file(file.getContent(), file.getExt(), null);
        String filePath=info[0]+"/"+info[1];
        return filePath;
    }

    /**
     * @Author lipu
     * @Date 2020/10/12 16:52
     * @Description 文件下载
     */
    public static InputStream downloadFile(String groupName, String fileName) throws Exception {
        //创建Tracker客户端对象
        TrackerClient trackerClient=new TrackerClient();
        //通过Tracker对象获取连接信息
        TrackerServer trackerServer = trackerClient.getConnection();
        //获取一个Storage的信息
        StorageServer storage = trackerClient.getStoreStorage(trackerServer);
        //根据连接信息获取storage客户端对象
        StorageClient storageClient = new StorageClient(trackerServer, storage);
        //文件下载
        byte[] bytes = storageClient.download_file(groupName, fileName);
        InputStream inputStream= new ByteArrayInputStream(bytes);
        return inputStream;
    }

    /**
     * @Author lipu
     * @Date 2020/10/12 16:50
     * @Description 获取文件信息
     */
    public static FileInfo getFile(String groupName,String fileName) throws Exception {
        //创建Tracker客户端对象
        TrackerClient trackerClient=new TrackerClient();
        //通过Tracker对象获取连接信息
        TrackerServer trackerServer = trackerClient.getConnection();
        //获取一个Storage的信息
        StorageServer storage = trackerClient.getStoreStorage(trackerServer);
        //根据连接信息获取storage客户端对象
        StorageClient storageClient = new StorageClient(trackerServer, storage);
        /**
         * 获取文件信息
         * 参数1.文件的组名 例如group1
         * 参数2.文件的名字 存储在Storage上的文件名 例如M00/11/22/bird.jpg
         */
        FileInfo fileInfo = storageClient.get_file_info(groupName, fileName);
        return fileInfo;
    }

    /**
     * @Author lipu
     * @Date 2020/10/12 16:58
     * @Description 删除文件
     */
    public static Integer deleteFile(String groupName, String fileName) throws Exception{
        //创建Tracker客户端对象
        TrackerClient trackerClient=new TrackerClient();
        //通过Tracker对象获取连接信息
        TrackerServer trackerServer = trackerClient.getConnection();
        //获取一个Storage的信息
        StorageServer storage = trackerClient.getStoreStorage(trackerServer);
        //根据连接信息获取storage客户端对象
        StorageClient storageClient = new StorageClient(trackerServer, storage);
        //文件删除
        Integer result = storageClient.delete_file(groupName, fileName);
        return result;
    }
}
