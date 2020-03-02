package com.github.vanlla.core.web;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 日志请请求拦截
 *
 * @author Vanlla
 * @since 1.0
 */
@Component
@Order(0)
public class RequestLogFilter implements Filter {
    public RequestLogFilter() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest && RestLog.isDebugEnabled()) {
            HttpRequestWrapper req = new HttpRequestWrapper((HttpServletRequest) request);
            RestLog.logRequest(req);
            chain.doFilter(req, response);
        } else {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}