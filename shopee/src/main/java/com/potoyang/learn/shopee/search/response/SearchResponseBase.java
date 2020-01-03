package com.potoyang.learn.shopee.search.response;

import com.potoyang.learn.shopee.common.HttpResponseBase;
import com.potoyang.learn.shopee.item.response.ItemBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2020/1/3 11:02
 * Modified:
 * Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SearchResponseBase extends HttpResponseBase {
    private String algorithm;

    private Integer total_count;
    private Integer total_ads_count;
    private Boolean nomore;
    private String json_data;
    private Integer suggestion_algorithm;
    private String reserved_keyword;
    private List<String> hint_keywords;

    private Boolean show_disclaimer;

    private QueryRewriteBase query_rewrite;

    private AdjustBase adjust;

    private List<ItemBase> items;
}
