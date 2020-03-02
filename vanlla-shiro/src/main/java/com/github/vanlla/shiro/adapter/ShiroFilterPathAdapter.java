package com.github.vanlla.shiro.adapter;

/**
 * shiro path 配置接口
 *
 * @author Vanlla
 * @since 1.0
 */
public interface ShiroFilterPathAdapter {

    /**
     * 不需要权限处理配置接口
     *
     * @return
     */
    String[] getAnonConfig();

    /**
     * 需要shiro
     *
     * @return
     */
    String[] getOAuth2Config();
}