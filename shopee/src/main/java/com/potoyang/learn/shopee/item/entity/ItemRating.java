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
 * @since 2020/1/3 14:39
 * Modified:
 * Description:
 */
@Data
@Table(name = "item_rating")
@Entity
@DynamicInsert
@DynamicUpdate
public class ItemRating implements Serializable {
    private static final long serialVersionUID = 7163767398479792106L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rating_star", columnDefinition = "float(10,6) NOT NULL comment '评分(保留6位小数)'")
    private Float ratingStar;

    @Column(name = "rating_count", columnDefinition = "varchar(30) comment '评分列表'")
    private String rating_count;

    @Column(name = "rcount_with_image", columnDefinition = "int(11) comment '带图评价数'")
    private Integer rcountWithImage;

    @Column(name = "rcount_with_context", columnDefinition = "int(11) comment 'xx评价数'")
    private Integer rcountWithContext;
}
