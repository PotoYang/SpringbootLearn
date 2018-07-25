package com.potoyang.learn.springbootfirstapplication;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/7/24 15:51
 * Modified By:
 * Description:
 */
@RestController
public class HelloController {
    @RequestMapping("hello")
    public String hello() {
        return "Hello";
    }
}
