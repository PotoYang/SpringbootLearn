package com.potoyang.learn.springbootfirstapplication;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/9/27 11:14
 * Modified By:
 * Description:
 */
public interface UserManager {
    void insertUser(User user);

    User findUserByUsername(String username);

    List<User> findUsersByUsername(String username);

    List<User> findAll();
}
