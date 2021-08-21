package com.potoyang.learn.shopee.item.entity;

import com.potoyang.learn.shopee.catagory.entity.Category;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
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
@Table(name = "item")
@Entity
@DynamicInsert
@DynamicUpdate
@Builder
public class Item implements Serializable {
    private static final long serialVersionUID = 7696531798685299231L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "shopid", columnDefinition = "int(11) NOT NULL comment '店铺id'")
    private Long shopid;

    @Column(name = "itemid", columnDefinition = "int(11) NOT NULL comment '商品id'")
    private Long itemid;

    @Column(name = "name", columnDefinition = "varchar(100) NOT NULL comment '商品名称'")
    private String name;

    @Column(name = "ctime", columnDefinition = "bigint NOT NULL comment '商品创建时间(s)'")
    private Long ctime;

    @Column(name = "welcome_package_info", columnDefinition = "varchar(100) comment 'xxx'")
    private String welcomePackageInfo;

    @Column(name = "recommendation_info", columnDefinition = "varchar(100) comment 'xxx'")
    private String recommendationInfo;

    @Column(name = "bundle_deal_info", columnDefinition = "varchar(100) comment 'xxx'")
    private String bundleDealInfo;

    @Column(name = "group_buy_info", columnDefinition = "varchar(100) comment 'xxx'")
    private String groupBuyInfo;

    @Column(name = "liked", columnDefinition = "boolean default false comment '是否喜欢'")
    private Boolean liked;

    @Column(name = "price_max_before_discount", columnDefinition = "int(11) NOT NULL comment '折扣前最高价格'")
    private Integer priceMaxBeforeDiscount;

    @Column(name = "price_min_before_discount", columnDefinition = "int(11) NOT NULL comment '折扣前最低价'")
    private Integer priceMinBeforeDiscount;

    @Column(name = "price_before_discount", columnDefinition = "int(11) NOT NULL comment '折扣前价格'")
    private Integer priceBeforeDiscount;

    @Column(name = "image", columnDefinition = "varchar(50) comment '主图'")
    private String image;

    @Column(name = "is_cc_installment_payment_eligible", columnDefinition = "boolean default false comment '是否支持 cc 分期付款'")
    private Boolean isCcInstallmentPaymentEligible;

    @Column(name = "is_non_cc_installment_payment_eligible", columnDefinition = "boolean default false comment '是否支持非 cc 分期付款'")
    private Boolean isNonCcInstallmentPaymentEligible;

    @Column(name = "can_use_wholesale", columnDefinition = "boolean default false comment '是否用于批发'")
    private Boolean canUseWholesale;

    @Column(name = "reference_item_id", columnDefinition = "varchar(20) default '' comment '关联商品id'")
    private String referenceItemId;

    @Column(name = "currency", columnDefinition = "varchar(20) default 'RMB' comment '货币类型'")
    private String currency;

    @Column(name = "raw_discount", columnDefinition = "smallint(8) default 0 comment '原始折扣'")
    private Integer rawDiscount;

    @Column(name = "show_free_shipping", columnDefinition = "boolean default false comment '是否免邮'")
    private Boolean showFreeShipping;

    @Column(name = "ads_keyword", columnDefinition = "varchar(100) default NULL comment '广告关键词'")
    private String adsKeyword;

    @Column(name = "images", columnDefinition = "varchar(1000) default NULL comment '商品图片'")
    private String images;

    @Column(name = "collection_id", columnDefinition = "varchar(100) default NULL comment 'xxx'")
    private String collectionId;

    @Column(name = "match_type", columnDefinition = "varchar(100) default NULL comment 'xxx'")
    private String matchType;

    @Column(name = "is_category_failed", columnDefinition = "boolean default 0 comment '是否分类失败'")
    private Boolean isCategoryFailed;

    @Column(name = "show_discount", columnDefinition = "smallint default 0 comment '显示的折扣(%)'")
    private Integer showDiscount;

    @Column(name = "cmt_count", columnDefinition = "int comment 'xxx'")
    private Integer cmtCount;

    @Column(name = "view_count", columnDefinition = "bigint comment '点击量'")
    private Long viewCount;

    @Column(name = "display_name", columnDefinition = "varchar(100) comment 'xxx'")
    private String displayName;

    @Column(name = "json_data", columnDefinition = "varchar(300) comment 'xxx'")
    private String jsonData;

    @Column(name = "upcoming_flash_sale", columnDefinition = "varchar(300) comment '即将到来的闪购'")
    private String upcomingFlashSale;

    @Column(name = "is_official_shop", columnDefinition = "boolean comment '是否官方店铺'")
    private Boolean isOfficialShop;

//    @Column(name = "catid", columnDefinition = "int NOT NULL comment '类别id'")
//    private Integer catid;

    @Column(name = "brand", columnDefinition = "varchar(100) comment '品牌'")
    private String brand;

    @Column(name = "price_min", columnDefinition = "int(11) comment '最低价'")
    private Integer priceMin;

    @Column(name = "liked_count", columnDefinition = "int(11) comment '喜欢数量'")
    private Integer likedCount;

    @Column(name = "can_use_bundle_deal", columnDefinition = "boolean comment '能否捆绑销售'")
    private Boolean canUseBundleDeal;

    @Column(name = "show_official_shop_label", columnDefinition = "boolean comment '是否展示官方店铺图标'")
    private Boolean showOfficialShopLabel;

    @Column(name = "coin_earn_label", columnDefinition = "varchar(100) comment 'xxx'")
    private String coinEarnLabel;

    @Column(name = "cb_option", columnDefinition = "varchar(100) comment 'xxx'")
    private String cbOption;

    @Column(name = "sold", columnDefinition = "int(11) comment '已销售数量'")
    private Integer sold;

    @Column(name = "deduction_info", columnDefinition = "varchar(100) comment 'xxx'")
    private String deductionInfo;

    @Column(name = "stock", columnDefinition = "int(11) comment '库存数量'")
    private Integer stock;

    @Column(name = "status", columnDefinition = "smallint comment '商品状态'")
    private Short status;

    @Column(name = "price_max", columnDefinition = "int(11) comment '最高价格'")
    private Integer priceMax;

    @Column(name = "add_on_deal_info", columnDefinition = "varchar(100) comment 'xxx'")
    private String addOnDealInfo;

    @Column(name = "is_group_buy_item", columnDefinition = "varchar(100) comment 'xxx'")
    private String isGroupBuyItem;

    @Column(name = "price", columnDefinition = "int(11) NOT NULL comment '商品售价'")
    private Integer price;

    @Column(name = "shop_location", columnDefinition = "varchar(500) comment '店铺位置'")
    private String shopLocation;

    @Column(name = "show_official_shop_label_in_title", columnDefinition = "boolean comment '是否在标题中展示官方店铺标志'")
    private Boolean showOfficialShopLabelInTitle;

    @Column(name = "is_adult", columnDefinition = "boolean comment '是否成人用品'")
    private Boolean isAdult;

    @Column(name = "discount", columnDefinition = "varchar(10) comment '显示的折扣'")
    private String discount;

    @Column(name = "flag", columnDefinition = "smallint comment 'xxx'")
    private Short flag;

    @Column(name = "has_lowest_price_guarantee", columnDefinition = "boolean comment '是否有保值承诺'")
    private Boolean hasLowestPriceGuarantee;

    @Column(name = "has_group_buy_stock", columnDefinition = "varchar(100) comment 'xxx'")
    private String hasGroupBuyStock;

    @Column(name = "preview_info", columnDefinition = "varchar(100) comment 'xxx'")
    private String previewInfo;

    @Column(name = "welcome_package_type", columnDefinition = "int(11) comment 'xxx'")
    private Integer welcomePackageType;

    @Column(name = "distance", columnDefinition = "varchar(100) comment 'xxx'")
    private String distance;

    @Column(name = "adsid", columnDefinition = "bigint comment '广告 id'")
    private Long adsid;


    @Column(name = "show_shopee_verified_label", columnDefinition = "boolean comment '是否展示官方认证标签'")
    private Boolean show_shopee_verified_label;

    @Column(name = "campaignid", columnDefinition = "varchar(100) comment 'xxx'")
    private String campaignid;

    @Column(name = "show_official_shop_label_in_normal_position", columnDefinition = "varchar(100) comment 'xxx'")
    private String showOfficialShopLabelInNormalPosition;

    @Column(name = "item_status", columnDefinition = "varchar(20) comment '商品状态'")
    private String itemStatus;

    @Column(name = "shopee_verified", columnDefinition = "boolean comment '商品是否官方认证'")
    private Boolean shopeeVerified;

    @Column(name = "hidden_price_display", columnDefinition = "varchar(100) comment 'xxx'")
    private String hiddenPriceDisplay;

    @Column(name = "size_chart", columnDefinition = "varchar(100) comment 'xxx'")
    private String sizeChart;

    @Column(name = "item_type", columnDefinition = "int(11) comment '商品类型??'")
    private Integer item_type;

    @Column(name = "shipping_icon_type", columnDefinition = "varchar(100) comment 'xxx'")
    private String shippingIconType;

    @Column(name = "campaign_stock", columnDefinition = "varchar(100) comment 'xxx'")
    private String campaignStock;

    @Column(name = "label_ids", columnDefinition = "varchar(200) comment '商品标签 id'")
    private String label_ids;

    @Column(name = "service_by_shopee_flag", columnDefinition = "smallint comment 'xxx'")
    private Short serviceByShopeeFlag;

    @Column(name = "badge_icon_type", columnDefinition = "smallint comment '徽章图标类型'")
    private Short badgeIconType;

    @Column(name = "historical_sold", columnDefinition = "int(11) comment '历史销售数量'")
    private Integer historicalSold;

    @Column(name = "transparent_background_image", columnDefinition = "varchar(100) comment 'xxx'")
    private String transparent_background_image;


    @OneToMany
    private List<VideoInfo> videoInfoList;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "catid")
//    private Category category;

    // 闪购
//    @OneToMany(fetch = FetchType.LAZY, targetEntity = FlashSale.class)
//    @Cascade(value = {CascadeType.ALL})
//    @JoinColumn(name = "id")
//    private List<FlashSale> flashSale;

    // 商品评分
//    @OneToOne(fetch = FetchType.LAZY, targetEntity = ItemRating.class)
//    @Cascade(value = {CascadeType.ALL})
//    @JoinColumn(name = "id")
//    private ItemRating itemRating;

    // 关联参数
    @OneToMany
    private List<TierVariation> tierVariations;

//    @OneToMany(fetch = FetchType.LAZY, targetEntity = WholesaleTier.class)
//    @Cascade(value = {CascadeType.ALL})
//    @JoinColumn(name = "id")
//    private List<WholesaleTier> wholesaleTierList;
}