package com.potoyang.learn.websocketrabbitmq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/8/8 15:49
 * Modified By:
 * Description:
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter endpointExporter() {
        return new ServerEndpointExporter();
    }
}
