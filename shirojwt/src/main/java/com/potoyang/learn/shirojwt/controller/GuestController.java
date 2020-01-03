package com.potoyang.learn.shirojwt.controller;

import com.potoyang.learn.shirojwt.controller.response.RestResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/9/10 14:30
 * Modified By:
 * Description:
 */
@RestController
@RequestMapping("/guest")
public class GuestController {

    @RequestMapping(value = "/enter", method = RequestMethod.GET)
    public RestResult<String> guestEnter() {
        return new RestResult<>("欢迎进入，您的身份是游客");
    }

    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public RestResult<String> guestGetMessage() {
        return new RestResult<>("游客，您拥有获得该接口的信息的权限！");
    }
}
