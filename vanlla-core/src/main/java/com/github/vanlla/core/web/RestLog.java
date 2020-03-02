package com.github.vanlla.core.web;

import cn.hutool.core.util.StrUtil;
import com.github.vanlla.core.util.FastJsonUtils;
import com.github.vanlla.core.util.UUID32;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpResponse;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求日志记录
 *
 * @author Vanlla
 * @since 1.0
 */
public class RestLog {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestLog.class);
    private static ThreadLocal<Long> startTime = new ThreadLocal();
    private static ThreadLocal<String> requestId = new ThreadLocal();

    public RestLog() {
    }

    public static synchronized boolean isDebugEnabled() {
        return LOGGER.isDebugEnabled();
    }

    public static synchronized void logRequest(HttpRequestWrapper request) {

        if (request.getServletPath().indexOf("/swagger") <= -1 && request.getServletPath().indexOf("/druid") <= -1 && !"/info".equals(request.getServletPath()) && !"/health".equals(request.getServletPath())) {
            startTime.set(System.currentTimeMillis());
            requestId.set(UUID32.getID());
            if ((request.getContentType() == null || !request.getContentType().startsWith("multipart")) && request.getContentLength() <= 1000000) {
                StringBuilder cookieBudilers = new StringBuilder();
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (int i = 0; i < cookies.length; ++i) {
                        cookieBudilers.append(cookies[i].getName());
                        cookieBudilers.append("=");
                        cookieBudilers.append(cookies[i].getValue());
                        if (i < cookies.length - 1) {
                            cookieBudilers.append("; ");
                        }
                    }
                }

                Map logMap = new HashMap(1);
                logMap.put("Method", request.getMethod());
                logMap.put("Path", request.getServletPath());
                logMap.put("Host", request.getHeader("Host"));
                logMap.put("Content-Length", request.getContentLength());
                logMap.put("Cookies", cookieBudilers.toString());
                logMap.put("Referer", request.getHeader("Referer"));
                logMap.put("Content-Type", StrUtil.toString(request.getHeader("Content-Type")));
                logMap.put("User-Agent", request.getHeader("User-Agent"));
                logMap.put("Authorization", request.getHeader("Authorization"));
                LOGGER.debug("接收请求:" + requestId.get());
                LOGGER.debug(FastJsonUtils.toPrettyString(logMap));
                LOGGER.debug("接收内容:");
                LOGGER.debug(request.toString());
            }
        }

    }

    public static synchronized void logResponse(ServerHttpResponse response, Map result) {
        LOGGER.debug("请求:" + requestId.get() + ",执行" + (System.currentTimeMillis() - (Long) startTime.get()) + "毫秒，返回结果:");
        LOGGER.debug("\n" + StrUtil.replace(FastJsonUtils.toPrettyString(result), "\t", "  "));
        remove();
    }

    public static synchronized void logResponse(HttpServletResponse response, Map result) {
        LOGGER.debug("请求:" + requestId.get() + ",执行" + (System.currentTimeMillis() - (Long) startTime.get()) + "毫秒，返回结果:");
        LOGGER.debug("\n" + FastJsonUtils.toPrettyString(result));
        remove();
    }

    /**
     * 清除资源
     */
    public static void remove() {
        requestId.remove();
        startTime.remove();
    }
}