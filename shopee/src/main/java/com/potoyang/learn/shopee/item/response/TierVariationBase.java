package com.potoyang.learn.shopee.item.response;

import lombok.Data;

import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2020/1/3 14:44
 * Modified:
 * Description:
 */
@Data
public class TierVariationBase {
    // 图片列表
    private List<String> images;

    private String properties;

    private Integer type;

    // 参数类型
    private String name;

    // 选项列表
    private List<String> options;
}
