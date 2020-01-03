package com.potoyang.learn.shopee.item.response;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2020/1/3 12:04
 * Modified:
 * Description:
 */
@Data
public class VideoInfoBase implements Serializable {
    private static final long serialVersionUID = 6155585820575308380L;

    // 视频时长(s)
    private Integer duration;

    // 视频 id
    private String video_id;

    // 缩略图
    private String thumb_url;
}
