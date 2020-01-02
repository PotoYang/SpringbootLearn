package com.potoyang.learn.shopee;

import com.potoyang.learn.shopee.catagory.ShoppeCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopeeApplicationTests {

    @Autowired
    private ShoppeCategory shoppeCategory;

    @Test
    public void contextLoads() {
    }

    @Test
    public void getCategory() {
        shoppeCategory.getCategoryList();
    }

}
