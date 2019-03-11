package com.potoyang.learn.fileupload.config;

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
public class MultipartFileParam implements Serializable {
    private static final long serialVersionUID = 4449279197776004720L;

    /**
     * 总分片数量
     */
    private int chunks;
    /**
     * 当前为第几块分片
     */
    private int chunk;
    /**
     * 当前分片大小
     */
    private long size = 0L;
    /**
     * 文件名,例:xxx.mp4
     */
    private String name;
    /**
     * 分片对象
     */
    private MultipartFile file;
    /**
     * md5
     */
    private String md5;
    /**
     * chunkmd5
     */
    private String chunkmd5;

    @Override
    public String toString() {
        return "MultipartFileParam{" +
                "chunks=" + chunks +
                ", chunk=" + chunk +
                ", size=" + size +
                ", name='" + name + '\'' +
                ", file=" + file.getOriginalFilename() +
                ", md5='" + md5 + '\'' +
                ", chunkmd5='" + chunkmd5 + '\'' +
                '}';
    }
}
