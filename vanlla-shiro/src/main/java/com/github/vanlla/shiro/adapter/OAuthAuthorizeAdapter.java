package com.github.vanlla.shiro.adapter;


import com.github.vanlla.shiro.token.VanllaLoginToken;

/**
 * 定义token创建接口
 *
 * @author Vanlla
 * @since 1.0
 */
public interface OAuthAuthorizeAdapter {

    /**
     * 创建token
     *
     * @param userId
     * @return
     */
    VanllaLoginToken createToken(long userId);

}