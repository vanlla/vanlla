package com.github.vanlla.shiro.autoconfigure;

import com.github.vanlla.shiro.adapter.ShiroFilterPathAdapter;
import org.springframework.stereotype.Component;

/**
 * shiro 拦截路径配置
 *
 * @author Vanlla
 * @since 1.0
 */
@Component
public class ShiroFilterPathConfiguration implements ShiroFilterPathAdapter {

    @Override
    public String[] getAnonConfig() {
        return new String[]{
                "/webjars/**",
                "/druid/**",
                "/app/**",
                "/sys/login",
                "/user/login",
                "/sys/generator/**",
                "/v2/api-docs",
                "/swagger-ui.html",
                "/swagger-resources/**",
                "/generator/**",
                "/captcha.jpg",
                "/upload/**",
                "/blog/api/**",
        };
    }

    @Override
    public String[] getOAuth2Config() {
        return new String[]{
                "/**"
        };
    }
}