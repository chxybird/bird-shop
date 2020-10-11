package com.bird.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author lipu
 * @Date 2020/10/11 19:43
 * @Description FastDFS文件上传实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FastDFSFile implements Serializable {
    //文件名
    private String name;
    //文件内容
    private String content;
    //文件扩展名
    private String ext;
    //文件MD5摘要值
    private String md5;
    //文件创建作者
    private String author;

}
