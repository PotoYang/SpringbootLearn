package com.potoyang.learn.shopee.catagory.entity;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2020/1/3 10:17
 * Modified:
 * Description:
 */
@Data
@Entity
@Table(name = "category")
@Builder
@DynamicInsert
@DynamicUpdate
public class Category implements Serializable {
    private static final long serialVersionUID = -9214544154531567585L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "display_name", columnDefinition = "varchar(50) default '' comment '类别展示名称'")
    private String displayName;

    @Column(name = "catid", columnDefinition = "int(11) NOT NULL comment '类别id'")
    private Integer catid;

    @Column(name = "image", columnDefinition = "varchar(50) default '' comment '图片地址'")
    private String image;

    @Column(name = "no_sub", columnDefinition = "boolean default false comment '是否子类别'")
    private Boolean noSub;

    @Column(name = "is_default_subcat", columnDefinition = "tinyint default 0 comment '是否默认子类'")
    private Integer isDefaultSubcat;

    @Column(name = "block_buyer_platform", columnDefinition = "varchar(255) default NULL comment '不知道'")
    private String blockBuyerPlatform;

    @Column(name = "create_time", columnDefinition = "timestamp default CURRENT_TIMESTAMP comment '创建时间'", updatable = false)
    private Timestamp createTime;

    @Column(name = "update_time", columnDefinition = "timestamp default CURRENT_TIMESTAMP comment '更新时间'")
    private Timestamp updateTime;
}
