package com.github.vanlla.shiro.adapter;

import com.github.vanlla.core.exception.RestException;
import com.github.vanlla.shiro.oauth2.VanllaAuthToken;
import com.github.vanlla.shiro.token.VanllaLoginToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Set;

/**
 * Vanlla Realm 登录方式
 *
 * @author Vanlla
 * @since 1.0
 */
public abstract class VanllaRealmAdapter extends AuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof VanllaAuthToken;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        VanllaLoginToken userToken = this.getVanllaLoginToken((VanllaAuthToken) authenticationToken);
        //token失效
        if (userToken == null || userToken.getExpireTime().getTime() < System.currentTimeMillis()) {
            throw new RestException(-401, "token失效，请重新登录");
        }

        //TODO 判断用户状态
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userToken, userToken.getToken(), getName());
        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // SimpleAuthenticationInfo构造方法的第一个参数对象
        VanllaLoginToken loginToken = (VanllaLoginToken) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(getStringPermissions(loginToken));
        return info;
    }

    /**
     * 获取登录方式
     *
     * @return
     */
    public abstract int getLoginType();

    /**
     * 通过前端传过来的 VanllaAuthToken 获取对应的用户信息
     *
     * @param authToken 前端登录的authToken
     * @return
     */
    public abstract VanllaLoginToken getVanllaLoginToken(VanllaAuthToken authToken);

    /**
     * 获取登录之后的权限
     *
     * @param loginToken
     * @return
     */
    public abstract Set<String> getStringPermissions(VanllaLoginToken loginToken);

}
