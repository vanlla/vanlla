package com.github.vanlla.shiro.oauth2;

import cn.hutool.core.convert.Convert;
import com.github.vanlla.shiro.util.LoginType;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * VanllaAuthToken
 *
 * @author Vanlla
 * @since 1.0
 */
public class VanllaAuthToken implements AuthenticationToken {

    private String token;

    /**
     * 登录类型，默认前端登录方式
     */
    private int loginType;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }

    public VanllaAuthToken(String token) {
        this.token = token;
        this.loginType = token.length() == 33 ? Convert.toInt(token.substring(32)) : LoginType.WORK_WECHAT;
    }

    @Override
    public Object getPrincipal() {
        return this.token;
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }


}
