package com.github.vanlla.shiro.token;

import java.util.Date;

/**
 * Shiro登录Token
 *
 * @author Vanlla
 * @since 1.0
 */
public interface VanllaLoginToken {

    /**
     * 设置登录的用户ID
     *
     * @param userId
     */
    void setUserId(String userId);

    /**
     * 获取登录的用户ID
     */
    String getUserId();

    /**
     * 设置Token
     *
     * @param token
     */
    void setToken(String token);

    /**
     * 获取Token
     *
     * @return
     */
    String getToken();

    /**
     * 设置过期时间
     *
     * @param expireTime
     */
    void setExpireTime(Date expireTime);

    /**
     * 获取过期时间
     *
     * @return
     */
    Date getExpireTime();

    /**
     * 设置更新时间
     *
     * @param updateTime
     */
    void setUpdateTime(Date updateTime);

    /**
     * 获取更新时间
     *
     * @return
     */
    Date getUpdateTime();

}
