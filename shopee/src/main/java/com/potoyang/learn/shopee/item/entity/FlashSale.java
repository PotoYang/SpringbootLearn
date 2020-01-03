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
 * @since 2020/1/3 14:37
 * Modified:
 * Description:
 */
@Data
@Table(name = "flash_sale")
@Entity
@DynamicInsert
@DynamicUpdate
public class FlashSale implements Serializable {
    private static final long serialVersionUID = -7196844150419624322L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
