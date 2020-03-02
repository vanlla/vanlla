package com.github.vanlla.shiro.oauth2;

import com.github.vanlla.shiro.adapter.VanllaRealmAdapter;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;


/**
 * 多个Realm登录方式
 *
 * @author Vanlla
 * @since 1.0
 */
public class VanllaModularRealmAuthenticator extends ModularRealmAuthenticator {

    protected Logger LOGGER = LoggerFactory.getLogger(VanllaModularRealmAuthenticator.class);

    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        LOGGER.info("VanllaModularRealmAuthenticator:method doAuthenticate() execute ");
        // 判断getRealms()是否返回为空
        assertRealmsConfigured();
        // 强制转换回自定义的 VanllaAuthToken
        VanllaAuthToken vanllaAuthToken = (VanllaAuthToken) authenticationToken;
        // 登录类型
        int loginType = vanllaAuthToken.getLoginType();
        // 所有Realm
        Collection<Realm> realms = getRealms();
        // 登录类型对应的所有Realm
        Collection<Realm> typeRealms = new ArrayList<>();
        for (Realm realm : realms) {
            //获取对应的Realm登录方式
            if (realm instanceof VanllaRealmAdapter && ((VanllaRealmAdapter) realm).getLoginType() == loginType) {
                typeRealms.add(realm);
            }
        }
        // 判断是单Realm还是多Realm
        if (typeRealms.size() == 1) {
            LOGGER.info("doSingleRealmAuthentication() execute ");
            return doSingleRealmAuthentication(typeRealms.iterator().next(), vanllaAuthToken);
        } else {
            LOGGER.info("doMultiRealmAuthentication() execute ");
            return doMultiRealmAuthentication(typeRealms, vanllaAuthToken);
        }
    }
}
