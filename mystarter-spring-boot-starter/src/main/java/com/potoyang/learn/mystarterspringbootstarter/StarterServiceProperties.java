package com.potoyang.learn.mystarterspringbootstarter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/8/15 16:29
 * Modified By:
 * Description:
 */
@ConfigurationProperties("example.service")
public class StarterServiceProperties {
    private String config;

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }
}
