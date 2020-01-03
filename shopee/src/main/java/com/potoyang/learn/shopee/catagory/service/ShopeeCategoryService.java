package com.potoyang.learn.shopee.catagory.service;

import com.potoyang.learn.shopee.catagory.entity.Category;

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
public interface ShopeeCategoryService {
    void getCategoryList();

    Optional<Category> getCategoryById(Integer catid);

    Optional<Category> getCategoryByDisplayName(String displayName);

    List<Category> findCategoryList();
}
