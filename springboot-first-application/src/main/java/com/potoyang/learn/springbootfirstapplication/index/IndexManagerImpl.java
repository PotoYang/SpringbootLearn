package com.potoyang.learn.springbootfirstapplication.index;

import com.mongodb.client.result.DeleteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexManagerImpl.class);
    //    private final MongoTemplate mongoTemplate;
    private final MongoOperations mongoOperations;

    @Value("${upload.dir}")
    private String baseDir;

    @Autowired
    public IndexManagerImpl(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public void insertCarousel(Carousel carousel) {
        mongoOperations.insert(carousel);
    }

    @Override
    public List<Carousel> getCarousel() {
        return mongoOperations.findAll(Carousel.class);
    }

    @Override
    public String deleteCarousel(String path) {
        LOGGER.info("deleteCarousel() => " + path);
        Criteria criteria = Criteria.where("path").is(path);
        DeleteResult deleteResult = mongoOperations.remove(Query.query(criteria), Carousel.class);
        return "" + deleteResult.getDeletedCount();
    }

    @Override
    public String addImage(MultipartFile multipartFile) {

        return null;
    }
}
