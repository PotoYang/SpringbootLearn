package com.potoyang.learn.springbootfirstapplication.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/10/10 17:00
 * Modified By:
 * Description:
 */
@Service("indexManager")
public class IndexManagerImpl implements IndexManager {
    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void insertCarousel(Carousel carousel) {
        mongoOperations.insert(carousel);
    }

    @Override
    public List<Carousel> getCarousel() {
        return mongoOperations.findAll(Carousel.class);
    }
}
