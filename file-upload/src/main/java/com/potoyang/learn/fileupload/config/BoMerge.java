package com.potoyang.learn.fileupload.config;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/9/13 10:09
 * Modified By:
 * Description:
 */
@Data
public class BoMerge implements Serializable {
    private static final long serialVersionUID = -1772191842566535492L;

    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 视频名称
     */
    private String name;
    /**
     * 视频md5
     */
    private String md5;
    /**
     * 视频总分片数
     */
    private Integer chunks;
    /**
     * 视频总大小
     */
    private Long size = 0L;
}
