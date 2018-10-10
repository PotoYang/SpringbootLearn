package com.potoyang.learn.springbootfirstapplication.index;

import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/10/10 16:59
 * Modified By:
 * Description:
 */
public interface IndexManager {

    void insertCarousel(Carousel carousel);

    List<Carousel> getCarousel();
}
