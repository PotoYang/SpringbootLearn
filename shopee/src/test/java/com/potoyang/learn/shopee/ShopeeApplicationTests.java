package com.potoyang.learn.shopee;

import com.potoyang.learn.shopee.catagory.service.ShopeeCategoryService;
import com.potoyang.learn.shopee.item.service.ShopeeItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopeeApplicationTests {

    @Autowired
    private ShopeeCategoryService shopeeCategoryService;

    @Autowired
    private ShopeeItemService shopeeItemService;

    @Test
    public void getCategory() {
        shopeeCategoryService.getCategoryList();
    }

    @Test
    public void getCategoryItem() {
        shopeeItemService.getItemByCategory();
    }

}
