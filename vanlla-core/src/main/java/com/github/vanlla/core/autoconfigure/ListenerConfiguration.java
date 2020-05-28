package com.github.vanlla.core.autoconfigure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;


/**
 * 监听器
 *
 * @author Vanlla
 * @since 1.0
 */
@Configuration
public class ListenerConfiguration {

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }
}
