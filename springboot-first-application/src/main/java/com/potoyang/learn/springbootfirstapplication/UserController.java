package com.potoyang.learn.springbootfirstapplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/9/27 13:41
 * Modified By:
 * Description:
 */
@CrossOrigin
@RestController
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserManager userManager;

    @PostMapping("reg")
    public RestResult<User> reg(User user) {
        System.out.println(user);
        LOGGER.info("==> reg()");
        userManager.insertUser(user);
        return new RestResult<>(user);
    }

    @PostMapping("login")
    public RestResult<User> login(User user) {
        LOGGER.info("==> login()");
        User result = userManager.findUserByUsername(user.getUsername());
        if (result == null) {
            return new RestResult<>("不存在", RestResult.FAIL, null);
        }
        if (result.getPassword().equals(user.getPassword())) {
            return new RestResult<>(result);
        } else {
            return new RestResult<>("不匹配", RestResult.FAIL, null);
        }
    }
}
