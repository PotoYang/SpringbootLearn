package com.potoyang.learn.fileupload.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/7/12 15:04
 * Modified By: 删除uid和id
 * Description:
 */
@Data
public class MyFileParam implements Serializable {
    private static final long serialVersionUID = -4330659247262858864L;

    //当前为第几块分片
    private int chunkNum;
    // 分片对象
    private MultipartFile file;
    // chunkmd5
    private String chunkMd5;

    @Override
    public String toString() {
        return "MyFileParam{" +
                "chunkNum=" + chunkNum +
                ", file=" + file.getOriginalFilename() +
                ", chunkMd5='" + chunkMd5 + '\'' +
                '}';
    }
}
