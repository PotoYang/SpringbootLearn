package com.potoyang.learn.shopee.item.entity;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2020/1/3 14:44
 * Modified:
 * Description:
 */
@Data
@Table(name = "tier_variation")
@Entity
@DynamicInsert
@DynamicUpdate
@Builder
public class TierVariation implements Serializable {
    private static final long serialVersionUID = -4365445381214531339L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "images", columnDefinition = "varchar(1000) comment '图片列表'")
    private String images;

    @Column(name = "properties", columnDefinition = "varchar(1000) comment 'xxx'")
    private String properties;

    @Column(name = "type", columnDefinition = "smallint comment '参数类型'")
    private Integer type;

    @Column(name = "name", columnDefinition = "varchar(100) comment '参数名称'")
    private String name;

    @Column(name = "options", columnDefinition = "varchar(1000) comment '选项列表'")
    private String options;

    @ManyToOne
    @JoinColumn(name = "itemid")
    private Item item;
}
