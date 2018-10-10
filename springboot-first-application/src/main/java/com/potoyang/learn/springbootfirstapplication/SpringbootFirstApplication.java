package com.potoyang.learn.springbootfirstapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringbootFirstApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootFirstApplication.class, args);
    }
}
