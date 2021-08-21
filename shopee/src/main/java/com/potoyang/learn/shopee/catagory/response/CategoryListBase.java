package com.potoyang.learn.shopee.catagory.response;

import com.potoyang.learn.shopee.catagory.entity.Category;
import lombok.Data;

import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2020/1/3 00:36
 * Modified:
 * Description:
 */
@Data
public class CategoryListBase {
    private List<CategoryBase> category_list;
}
