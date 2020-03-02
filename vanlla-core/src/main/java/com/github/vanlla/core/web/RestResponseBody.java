package com.github.vanlla.core.web;

import com.github.vanlla.core.exception.RestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求返回结果拦截
 *
 * @author Vanlla
 * @since 1.0
 */
@Configuration
@ControllerAdvice(
        assignableTypes = {BaseRestController.class}
)
@ConditionalOnBean({RestResponseBody.class})
public class RestResponseBody implements ResponseBodyAdvice<Object> {
    @Autowired
    RestExceptionAdapter adapter;
    private static final Logger LOGGER = LoggerFactory.getLogger(RestResponseBody.class);

    public RestResponseBody() {
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object object, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {


        if (object == null) {
            object = new HashMap(1);
        }

        Map result;
        if (object instanceof RestException) {
            RestException exec = (RestException) object;
            if (!exec.isError() && exec.getCode() != 0) {
                LOGGER.warn("出现错误：" + exec.getMsg());
            }

            this.adapter.handle(exec);
            result = ((RestException) object).asMap();
        } else if (object instanceof RestStatus) {
            result = ((RestStatus) object).asMap();
        } else {
            result = RestStatus.SUCCESS.asMap();
            result.put("data", object);
        }

        if (RestLog.isDebugEnabled()) {
            try {
                RestLog.logResponse(response, result);
            } catch (Exception e) {
                LOGGER.error("LOG_RESPONSE_ERROR", e);
            }
        }

        return result;
    }

    @Bean
    @ConditionalOnMissingBean(
            name = {"restExceptionAdapter"}
    )
    RestExceptionAdapter restExceptionAdapter() {
        return new RestExceptionAdapter() {

            @Override
            public void handle(RestException exception) {
                LOGGER.debug("restExceptionAdapter handle" + exception.asString());
            }
        };
    }
}
