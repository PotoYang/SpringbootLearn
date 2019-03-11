package com.potoyang.learn.myfileupload.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/11 18:41
 * Modified:
 * Description:
 */
@Data
public class YUploadParam implements Serializable {
    private static final long serialVersionUID = -6165042554572107151L;

    // file 文件
    private MultipartFile file;
    // 文件 md5
    private String fileMd5;
    // 第几分片
    private Integer chunkNum;
    // 分片 md5
    private String chunkMd5;

    @Override
    public String toString() {
        return "YUploadParam{" +
                "file=" + file.getOriginalFilename() +
                ", fileMd5='" + fileMd5 + '\'' +
                ", chunkNum=" + chunkNum +
                ", chunkMd5='" + chunkMd5 + '\'' +
                '}';
    }
}
