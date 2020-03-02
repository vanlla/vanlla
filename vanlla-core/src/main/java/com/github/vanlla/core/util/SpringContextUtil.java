package com.github.vanlla.core.util;

import cn.hutool.core.net.NetUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * 上下文工具类
 *
 * @author Vanlla
 * @since 1.0
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {


    /**
     * 上下文对象实例
     */
    private static ApplicationContext applicationContext;

    /**
     * 当前实例运行的端口号
     */
    private static int port;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * 获取applicationContext
     *
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取 Bean.
     *
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean.
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }


    /**
     * 获取本机运行的server:port,例如：192:168:92:92:8080
     *
     * @return
     */
    public static String getHostAndPort() {
        return NetUtil.getLocalhostStr() + ":" + SpringContextUtil.port;
    }


    @Value("${server.port:8080}")
    public void setPort(int port) {
        SpringContextUtil.port = port;
    }

    /**
     * 获取当前实例运行的端口
     *
     * @return
     */
    public static int getPort() {
        return port;
    }
}