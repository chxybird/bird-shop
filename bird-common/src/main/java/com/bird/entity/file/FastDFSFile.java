package com.bird.entity.file;

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
    private byte[] content;
    //文件扩展名
    private String ext;

}
