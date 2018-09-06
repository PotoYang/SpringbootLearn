package com.potoyang.learn.fileupload;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/7/18 15:24
 * Modified By:
 * Description:
 */
@SpringBootApplication
@MapperScan("com.potoyang.learn.fileupload.mapper")
@EnableAsync
@EnableScheduling
public class FileUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileUploadApplication.class, args);
    }
}
