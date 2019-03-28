package com.potoyang.learn.blog.global;

import com.potoyang.learn.blog.config.MyConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/28 14:08
 * Modified:
 * Description:
 */
@Component
@Slf4j
@Order(1)
public class BlogApplicationStart implements CommandLineRunner {

    private final MyConfig myConfig;

    @Autowired
    public BlogApplicationStart(MyConfig myConfig) {
        this.myConfig = myConfig;
    }

    @Override
    public void run(String... args) {
        createNeededFile();
    }

    private void createNeededFile() {
        try {
            String filePath = myConfig.getBlogSaveDir();
            File dir = new File(filePath);
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    throw new IOException("Dir create failed!");
                }
            }
            log.info("BlogDir has been created[{}].", filePath);
        } catch (IOException e) {
            log.error("ErrorMsg:{}", e.getMessage());
            System.exit(0);
        }
    }
}
