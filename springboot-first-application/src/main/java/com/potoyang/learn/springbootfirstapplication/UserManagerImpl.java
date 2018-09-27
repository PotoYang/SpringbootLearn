package com.potoyang.learn.springbootfirstapplication;

import com.mongodb.DBCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/9/27 11:17
 * Modified By:
 * Description:
 */
@Service("userManager")
public class UserManagerImpl implements UserManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserManagerImpl.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Resource(name = "mongoTemplate")
    private MongoOperations mongoOperations;

    private DBCollection getCollection(String collectionName) {
        return (DBCollection) mongoOperations.getCollection(collectionName);
    }

    @Override
    public void insertUser(User user) {
        mongoOperations.insert(user);
    }

    @Override
    public User findUserByUsername(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        return mongoOperations.findOne(query, User.class);
    }

    @Override
    public List<User> findUsersByUsername(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        return mongoOperations.find(query, User.class);
    }

    @Override
    public List<User> findAll() {
        return mongoOperations.findAll(User.class);
    }
}
