package com.potoyang.learn.shopee.config;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2020/1/3 00:15
 * Modified:
 * Description:
 */
public class Constant {
    public static final String HOST = "https://shopee.sg";

    public static class API {
        // 获取类别列表
        public static final String GET_CATEGORY_LIST = "/api/v2/category_list/get";

        // 获取类别下商品列表
        public static final String GET_CATEGORY_ITEM_LIST = "/api/v2/search_items/?";
    }
}
