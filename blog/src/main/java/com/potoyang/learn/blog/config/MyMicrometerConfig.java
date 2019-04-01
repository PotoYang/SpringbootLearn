package com.potoyang.learn.blog.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/29 12:03
 * Modified:
 * Description:
 */
@Configuration
public class MyMicrometerConfig {

    @Bean
    MeterRegistryCustomizer<MeterRegistry> meterRegistryCustomizer() {
        return (registry -> registry.config().commonTags("application", "blogApplication"));
    }
}
