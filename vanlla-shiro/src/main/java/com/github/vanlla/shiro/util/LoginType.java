package com.github.vanlla.shiro.util;

/**
 * 登录类型
 *
 * @author Vanlla
 * @since 1.0
 */
public interface LoginType {

    /**
     * 管理后台登录
     */
    int MANATER = 0;

    /**
     * 企业微信登录
     */
    int WORK_WECHAT = 1;

    /**
     * 未知登录
     */
    int UNKONWN = 999;
}
