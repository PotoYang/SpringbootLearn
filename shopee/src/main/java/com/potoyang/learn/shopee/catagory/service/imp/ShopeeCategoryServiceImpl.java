package com.potoyang.learn.shopee.catagory.service.imp;

import com.alibaba.fastjson.JSON;
import com.potoyang.learn.shopee.catagory.entity.Category;
import com.potoyang.learn.shopee.catagory.repository.ShopeeCategoryRepository;
import com.potoyang.learn.shopee.catagory.response.CategoryBase;
import com.potoyang.learn.shopee.catagory.response.CategoryResponseBase;
import com.potoyang.learn.shopee.catagory.service.ShopeeCategoryService;
import com.potoyang.learn.shopee.config.Constant;
import com.potoyang.learn.shopee.http.HttpClientService;
import com.potoyang.learn.shopee.http.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2020/1/3 00:10
 * Modified:
 * Description:
 */
@Service
public class ShopeeCategoryServiceImpl implements ShopeeCategoryService {

    private static final Logger log = LoggerFactory.getLogger(ShopeeCategoryService.class);

    private final HttpClientService httpClientService;
    private final ShopeeCategoryRepository shopeeCategoryRepository;

    @Autowired
    public ShopeeCategoryServiceImpl(HttpClientService httpClientService, ShopeeCategoryRepository shopeeCategoryRepository) {
        this.httpClientService = httpClientService;
        this.shopeeCategoryRepository = shopeeCategoryRepository;
    }

    @Transactional
    @Override
    public void getCategoryList() {
        try {
            HttpResult result = httpClientService.doGet(Constant.HOST.concat(Constant.API.GET_CATEGORY_LIST));
            if (result.getCode() != 200) {
                log.warn("Get Category Failed. Code[{}]", result.getCode());
                return;
            }

            CategoryResponseBase responseData = JSON.parseObject(result.getBody(), CategoryResponseBase.class);
            List<CategoryBase> categoryBaseList = responseData.getData().getCategory_list();
            List<Category> categoryList = new ArrayList<>();
            if (categoryBaseList != null && categoryBaseList.size() != 0) {
                categoryBaseList.forEach(categoryBase -> {
                    Category category = Category.builder()
                            .displayName(categoryBase.getDisplay_name())
                            .catid(categoryBase.getCatid())
                            .image(categoryBase.getImage())
                            .noSub(categoryBase.getNo_sub())
                            .isDefaultSubcat(categoryBase.getIs_default_subcat())
                            .blockBuyerPlatform(categoryBase.getBlock_buyer_platform())
                            .build();
                    categoryList.add(category);
                });
                saveCategoryListData(categoryList);
                log.info("Save Category Data Success[{}].", categoryBaseList.size());
            } else {
                log.warn("Save Category Data is null or 0!");
            }
        } catch (Exception e) {
            log.error("GetCategoryList Exception[{}].", e.getMessage());
        }
    }

    @Override
    public Optional<Category> getCategoryById(Integer catid) {
        return shopeeCategoryRepository.findByCatid(catid);
    }

    @Override
    public Optional<Category> getCategoryByDisplayName(String displayName) {
        return shopeeCategoryRepository.findByDisplayName(displayName);
    }

    @Override
    public List<Category> findCategoryList() {
        return (List<Category>) shopeeCategoryRepository.findAll();
    }

    private void saveCategoryListData(List<Category> categoryList) {
        shopeeCategoryRepository.saveAll(categoryList);
    }

}
