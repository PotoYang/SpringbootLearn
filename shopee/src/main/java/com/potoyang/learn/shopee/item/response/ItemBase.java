package com.potoyang.learn.shopee.item.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2020/1/3 10:54
 * Modified:
 * Description:
 */
@Data
public class ItemBase implements Serializable {
    private static final long serialVersionUID = 6699567442922346896L;

    // 店铺 id
    private Long shopid;

    // 商品 id
    private Long itemid;

    // 商品名称
    private String name;

    // 商品创建时间(s)
    private Long ctime;

    private String welcome_package_info;
    private String recommendation_info;
    private String bundle_deal_info;
    private String group_buy_info;

    // 是否喜欢
    private Boolean liked;

    // 折扣前最高价格
    private Integer price_max_before_discount;

    // 折扣前最低价
    private Integer price_min_before_discount;

    // 折扣前价格
    private Integer price_before_discount;

    // 主图
    private String image;

    // 是否支持 cc 分期付款
    private Boolean is_cc_installment_payment_eligible;

    // 是否支持非 cc 分期付款
    private Boolean is_non_cc_installment_payment_eligible;

    // 是否用于批发
    private Boolean can_use_wholesale;

    // 关联商品id
    private String reference_item_id;

    // 货币
    private String currency;

    // 原始折扣(%)
    private Integer raw_discount;

    // 是否免邮
    private Boolean show_free_shipping;

    // 广告关键词
    private String ads_keyword;

    private String collection_id;

    // 全部图片
    private List<String> images;

    // 视频信息
    private List<VideoInfoBase> video_info_list;

    private String match_type;

    // 是否分类失败
    private Boolean is_category_failed;

    // 显示的折扣(%)
    private Integer show_discount;

    private Integer cmt_count;

    // 点击量
    private Integer view_count;

    // 展示的名称
    private String display_name;

    // 分类id
    private Integer catid;

    private String json_data;

    private String upcoming_flash_sale;

    // 是否官方店铺
    private Boolean is_official_shop;

    // 品牌
    private String brand;

    // 最低价
    private Integer price_min;

    // 喜欢数量
    private Integer liked_count;

    // 能否捆绑销售
    private Boolean can_use_bundle_deal;

    // 是否展示官方店铺图标
    private Boolean show_official_shop_label;

    private String coin_earn_label;

    private String cb_option;

    // 已销售数量
    private Integer sold;

    private String deduction_info;

    // 库存数量
    private Integer stock;

    // 商品状态
    private Short status;

    // 最高价格
    private Integer price_max;

    private String add_on_deal_info;

    private String is_group_buy_item;

    private FlashSaleBase flash_sale;

    // 商品售价
    private Integer price;

    // 店铺位置
    private String shop_location;

    // 商品评分
    private ItemRatingBase item_rating;

    // 是否在标题中展示官方店铺标志
    private Boolean show_official_shop_label_in_title;

    // 关联参数
    private List<TierVariationBase> tier_variations;

    // 是否成人用品
    private Boolean is_adult;

    // 显示的折扣
    private String discount;

    // ??
    private Short flag;

    // 是否有保值
    private Boolean has_lowest_price_guarantee;

    private String has_group_buy_stock;

    private String preview_info;

    private Integer welcome_package_type;

    private String distance;

    // 广告 id
    private Integer adsid;

    private List<WholesaleTierBase> wholesale_tier_list;

    // 是否展示官方认证标签
    private Boolean show_shopee_verified_label;

    private String campaignid;

    private String show_official_shop_label_in_normal_position;

    // 商品状态
    private String item_status;

    // 商品是否官方认证
    private Boolean shopee_verified;

    private String hidden_price_display;

    private String size_chart;

    // 商品类型??
    private Integer item_type;

    private String shipping_icon_type;

    private String campaign_stock;

    // 商品标签 id
    private List<Integer> label_ids;

    private Short service_by_shopee_flag;

    // 徽章图标类型
    private Short badge_icon_type;

    // 历史销售数量
    private Integer historical_sold;

    private String transparent_background_image;
}
