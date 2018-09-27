package com.potoyang.learn.shiro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/9/19 17:01
 * Modified By:
 * Description:
 */
@Service
public class ProductService {

    @Autowired
    private UserService userService;
    @Autowired
    private MySessionService mySessionService;

    public User getUsername(String sessionId) {
        return userService.findByUsername(mySessionService.getUsername(sessionId));
    }
}
