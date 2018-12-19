package com.potoyang.learn.fileupload.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/12/5 14:13
 * Modified:
 * Description:
 */
@Configuration
@ConfigurationProperties(prefix = "resource")  //配置文件中的前缀
@PropertySource(value = "file:F:/settings.json")
public class ResourcesUtil {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

