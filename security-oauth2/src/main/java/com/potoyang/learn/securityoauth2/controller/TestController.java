package com.potoyang.learn.securityoauth2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @date 2019/7/1 15:42
 * Modified:
 * Description:
 */
@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/test/{id}")
    public String getById(@PathVariable String id) {
        return "Test Get By Id: " + id;
    }
}
