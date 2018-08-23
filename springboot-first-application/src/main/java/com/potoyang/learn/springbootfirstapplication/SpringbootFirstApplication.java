package com.potoyang.learn.springbootfirstapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class SpringbootFirstApplication {

    @GetMapping("/article/get/{id}")
    public Map get(@PathVariable int id) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("id", id);
        map.put("name", "test");
        map.put("desc", "testDesc");
        return map;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootFirstApplication.class, args);
    }
}
