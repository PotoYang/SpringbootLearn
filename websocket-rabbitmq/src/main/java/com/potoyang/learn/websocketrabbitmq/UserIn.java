package com.potoyang.learn.websocketrabbitmq;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/8/9 12:16
 * Modified By:
 * Description:
 */
@RestController
public class UserIn {

    @RequestMapping("login")
    public String login() {
        return "/index";
    }
}
