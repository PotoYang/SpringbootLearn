package com.potoyang.learn.springbootfirstapplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/9/27 11:54
 * Modified By:
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserManagerTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserManagerTest.class);

    @Autowired
    private UserManager userManager;

    @Test
    public void insertUser() {
        User user = new User();
        user.setUsername("Qoo");
        user.setPassword("Qoo");
        LOGGER.info("----insert begin----");
        userManager.insertUser(user);
        LOGGER.info("----insert end------");
    }

    @Test
    public void refreshCache() {
    }

    @Test
    public void findUserByUsername() {
    }

    @Test
    public void findUsersByUsername() {
    }

    @Test
    public void findAll() {
    }
}
