package com.potoyang.learn.shopee.catagory.response;

import com.potoyang.learn.shopee.common.HttpResponseBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2020/1/3 00:18
 * Modified:
 * Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryResponseBase extends HttpResponseBase {
    private CategoryListBase data;
}
