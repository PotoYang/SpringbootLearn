package com.potoyang.learn.myfileupload.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/11 19:12
 * Modified:
 * Description:
 */
@SuppressWarnings("ALL")
@Configuration
@ConfigurationProperties(prefix = "config")
@Data
public class MyConfig {
    private Map<String, Object> system;

    public String getFileUploadDir(){
        return (String) system.get("file-upload-dir");
    }
}
