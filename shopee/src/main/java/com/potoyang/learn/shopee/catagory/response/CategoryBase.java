package com.potoyang.learn.shopee.catagory.response;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2020/1/3 10:17
 * Modified:
 * Description:
 */
@Data
public class CategoryBase implements Serializable {
    private static final long serialVersionUID = -534807722351339064L;

    // 呈现的类别名称
    private String display_name;

    // 类别 id
    private Integer catid;

    // 类别图片
    private String image;

    // 是否有子类别
    private Boolean no_sub;

    // 是否默认子类别
    private Integer is_default_subcat;

    // 不知道
    private String block_buyer_platform;
}
