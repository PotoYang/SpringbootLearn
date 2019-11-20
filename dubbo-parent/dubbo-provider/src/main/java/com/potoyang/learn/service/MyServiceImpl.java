package com.potoyang.learn.service;

import com.potoyang.learn.MyService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/11/19 15:28
 * Modified:
 * Description:
 */
@Service(version = "1.0.0")
public class MyServiceImpl implements MyService {

    @Value("${dubbo.application.name}")
    private String serviceName;

    @Override
    public String sayHello(String name) {
        return String.format("[%s]: hello, %s", serviceName, name);
    }
}
