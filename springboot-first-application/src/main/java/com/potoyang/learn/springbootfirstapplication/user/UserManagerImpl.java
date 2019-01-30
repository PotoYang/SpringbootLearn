package com.potoyang.learn.springbootfirstapplication.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

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
    private MongoOperations mongoOperations;

    @Override
    public void insertUser(User user) {
        user.setAvatar("../static/img/cjy.jpg");
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

    @Override
    public void update(List<User> users) {
        List<User> userList = findAll();
        userList.forEach(user -> {
            Query query = new Query(Criteria.where("id").is(user.getId()));
//            Update.Modifier
            Update update = new Update();
            update.set("phone", "654");
            mongoOperations.updateFirst(query, update, User.class);
        });
    }
}
