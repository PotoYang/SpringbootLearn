package com.potoyang.learn.shopee.item.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
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
@Table(name = "video_info")
@Entity
@DynamicInsert
@DynamicUpdate
public class VideoInfo implements Serializable {
    private static final long serialVersionUID = 2642747139897294205L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "duration", columnDefinition = "int(11) NOT NULL comment '视频时长(s)'")
    private Integer duration;

    @Column(name = "video_id", columnDefinition = "varchar(20) NOT NULL comment '视频 id'")
    private String videoId;

    @Column(name = "thumb_url", columnDefinition = "varchar(50) NOT NULL comment '缩略图'")
    private String thumbUrl;

    @ManyToOne
    @JoinColumn(name = "itemid")
    private Item item;
}
