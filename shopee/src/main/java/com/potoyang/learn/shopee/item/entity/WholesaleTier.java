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
 * @since 2020/1/3 15:00
 * Modified:
 * Description:
 */
@Data
@Table(name = "wholesale_tier")
@Entity
@DynamicInsert
@DynamicUpdate
public class WholesaleTier implements Serializable {
    private static final long serialVersionUID = -6456200168588695997L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
