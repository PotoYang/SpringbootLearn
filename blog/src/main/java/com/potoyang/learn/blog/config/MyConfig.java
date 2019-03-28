package com.potoyang.learn.blog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/28 14:05
 * Modified:
 * Description:
 */
@Configuration
@ConfigurationProperties(prefix = "config")
@Data
public class MyConfig {
    private String blogSaveDir;

    public String getBlogSaveDir() {
        return blogSaveDir;
    }
}
