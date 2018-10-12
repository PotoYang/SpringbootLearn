package com.potoyang.learn.springbootfirstapplication;

import com.potoyang.learn.springbootfirstapplication.index.Carousel;
import com.potoyang.learn.springbootfirstapplication.index.IndexManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/10/10 17:11
 * Modified By:
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IndexManagerImplTest {

    @Autowired
    private IndexManager indexManager;

    @Test
    public void insertCarousel() {
        for (int i = 0; i < 6; i++) {
            Carousel carousel = new Carousel();
            carousel.setPath("../static/img/" + i + ".jpg");
            indexManager.insertCarousel(carousel);
            System.out.println("insert test success");
        }
    }

    @Test
    public void getCarousel() {
    }
}