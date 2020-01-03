package com.potoyang.learn.shopee.item.service.impl;

import com.alibaba.fastjson.JSON;
import com.potoyang.learn.shopee.catagory.entity.Category;
import com.potoyang.learn.shopee.catagory.service.ShopeeCategoryService;
import com.potoyang.learn.shopee.config.Constant;
import com.potoyang.learn.shopee.http.HttpClientService;
import com.potoyang.learn.shopee.http.HttpResult;
import com.potoyang.learn.shopee.item.entity.Item;
import com.potoyang.learn.shopee.item.entity.TierVariation;
import com.potoyang.learn.shopee.item.repository.ShopeeItemRepository;
import com.potoyang.learn.shopee.item.response.ItemBase;
import com.potoyang.learn.shopee.item.service.ShopeeItemService;
import com.potoyang.learn.shopee.search.response.SearchResponseBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2020/1/3 15:09
 * Modified:
 * Description:
 */
@Service
public class ShopeeItemServiceImpl implements ShopeeItemService {

    private final static Logger log = LoggerFactory.getLogger(ShopeeItemService.class);

    private final HttpClientService httpClientService;
    private final ShopeeCategoryService shopeeCategoryService;

    private final ShopeeItemRepository shopeeItemRepository;

    public ShopeeItemServiceImpl(HttpClientService httpClientService, ShopeeCategoryService shopeeCategoryService, ShopeeItemRepository shopeeItemRepository) {
        this.httpClientService = httpClientService;
        this.shopeeCategoryService = shopeeCategoryService;
        this.shopeeItemRepository = shopeeItemRepository;
    }

    @Override
    public void getItemByCategory() {
        List<Category> categoryList = shopeeCategoryService.findCategoryList();
        if (CollectionUtils.isEmpty(categoryList)) {
            throw new RuntimeException("Category is null!");
        }

        String firstCategoryName = categoryList.get(0).getDisplayName();
        Integer firstCatId = categoryList.get(0).getCatid();

        log.info("CategoryName[{}], catid[{}].", firstCategoryName, firstCatId);

        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Referer", Constant.HOST + "/" + firstCategoryName);

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("by", "pop");
        paramMap.put("limit", "10");
        paramMap.put("match_id", "" + firstCatId);
        paramMap.put("newest", "0");
        paramMap.put("order", "desc");
        paramMap.put("page_type", "search");
        paramMap.put("version", "2");

        try {
            HttpResult result = httpClientService.doGet(Constant.HOST + Constant.API.GET_CATEGORY_ITEM_LIST, headerMap, paramMap);
            if (result.getCode() != 200) {
                log.warn("Http error. Code[{}]", result.getCode());
                return;
            }

            SearchResponseBase responseData = JSON.parseObject(result.getBody(), SearchResponseBase.class);
            if (responseData != null && responseData.getItems() != null && responseData.getItems().size() != 0) {
                saveCategoryItemData(responseData.getItems());
            }
            log.info("CategoryItem[{}].", responseData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveCategoryItemData(List<ItemBase> items) {
        String delim = "|";
//        List<Item> itemList = new ArrayList<>();
        items.forEach(itemBase -> {
            List<TierVariation> tierVariationList = new ArrayList<>();
            itemBase.getTier_variations().forEach(tierVariationBase -> {
                TierVariation tierVariation = TierVariation.builder()
                        .images(StringUtils.collectionToDelimitedString(tierVariationBase.getImages(), delim))
                        .properties(tierVariationBase.getProperties())
                        .type(tierVariationBase.getType())
                        .name(tierVariationBase.getName())
                        .options(StringUtils.collectionToDelimitedString(tierVariationBase.getOptions(), delim))
                        .build();
                tierVariationList.add(tierVariation);
            });
            Item item = Item.builder()
                    .shopid(itemBase.getShopid())
                    .itemid(itemBase.getItemid())
                    .name(itemBase.getName())
                    .ctime(itemBase.getCtime())
                    .welcomePackageInfo(itemBase.getWelcome_package_info())
                    .recommendationInfo(itemBase.getRecommendation_info())
                    .bundleDealInfo(itemBase.getBundle_deal_info())
                    .groupBuyInfo(itemBase.getGroup_buy_info())
                    .images(StringUtils.collectionToDelimitedString(itemBase.getImages(), "|"))
                    .tierVariations(tierVariationList)
                    .build();

            shopeeItemRepository.save(item);
        });
    }
}
