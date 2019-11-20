package com.potoyang.learn.controller;

import com.potoyang.learn.MyService;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/11/19 15:36
 * Modified:
 * Description:
 */
@RestController
public class MyController {

    private static final Logger logger = LoggerFactory.getLogger(MyController.class);

    @Reference(version = "1.0.0")
    private MyService myService;

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name) {
        return myService.sayHello(name);
    }
}
