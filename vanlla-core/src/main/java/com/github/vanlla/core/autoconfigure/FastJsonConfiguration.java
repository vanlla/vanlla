package com.github.vanlla.core.autoconfigure;

import com.github.vanlla.core.util.FastJsonUtils;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Fast Json Configuration
 *
 * @author Vanlla
 * @since 1.0
 */
@Configuration
public class FastJsonConfiguration  {


    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        //这样会把FastJsonHttpMessageConverter放在集合的第一个，或者实现WebMvcConfigurer接口，重写 configureMessageConverters，把FastJsonHttpMessageConverter放到最前面 converters.add(0, FastJsonUtils.getConverter())
        return new HttpMessageConverters(FastJsonUtils.getConverter());
    }
}
