package com.potoyang.learn.shopee.item.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2020/1/3 17:33
 * Modified:
 * Description:
 */
@Data
public class ImageBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String url;
}
