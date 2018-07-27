package com.potoyang.learn.elk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ElkApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElkApplication.class, args);
    }
}
