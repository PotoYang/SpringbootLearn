package com.potoyang.learn.shopee.item.response;

import lombok.Data;

import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2020/1/3 14:39
 * Modified:
 * Description:
 */
@Data
public class ItemRatingBase {
    // 评分数(保留6位小数)
    private Float rating_star;

    // 评分列表
    private List<Integer> rating_count;

    // 带图评价数
    private Integer rcount_with_image;

    // xx评价数
    private Integer rcount_with_context;
}
