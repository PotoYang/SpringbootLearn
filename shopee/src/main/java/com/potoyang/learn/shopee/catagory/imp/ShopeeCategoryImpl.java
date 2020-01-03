package com.potoyang.learn.shopee.catagory.imp;

import com.alibaba.fastjson.JSON;
import com.potoyang.learn.shopee.catagory.ShoppeCategory;
import com.potoyang.learn.shopee.catagory.entity.CategoryResponse;
import com.potoyang.learn.shopee.config.Constant;
import com.potoyang.learn.shopee.http.HttpClientService;
import com.potoyang.learn.shopee.http.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2020/1/3 00:10
 * Modified:
 * Description:
 */
public class ShopeeCategoryImpl implements ShoppeCategory {

    private static final Logger log = LoggerFactory.getLogger(ShoppeCategory.class);

    private HttpClientService httpClientService;

    @Autowired
    public ShopeeCategoryImpl(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }

    @Override
    public void getCategoryList() {
        try {
            HttpResult result = httpClientService.doGet(Constant.HOST.concat(Constant.API.GET_CATEGORY_LIST));
            if (result.getCode() != 200) {
                log.warn("Get Category Failed. Code[{}]", result.getCode());
                return;
            }

            CategoryResponse responseData = JSON.parseObject(result.getBody(), CategoryResponse.class);
            log.info(responseData.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
