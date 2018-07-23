package com.potoyang.learn.fileupload;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
public class FileUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileUploadApplication.class, args);
    }
}
