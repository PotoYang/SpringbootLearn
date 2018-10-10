package com.potoyang.learn.springbootfirstapplication.user;

import com.potoyang.learn.springbootfirstapplication.RestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    private final UserManager userManager;

    @Autowired
    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    @PostMapping("reg")
    public RestResult<User> reg(@RequestBody User user) {
        System.out.println(user);
        LOGGER.info("==> reg()");
        userManager.insertUser(user);
        return new RestResult<>(userManager.findUserByUsername(user.getUsername()));
    }

    @PostMapping("login")
    public RestResult<User> login(@RequestBody User user, HttpServletRequest request) {
        LOGGER.info("==> login()" + request.getHeader("token"));
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
