package com.github.vanlla.realm;

import com.github.vanlla.shiro.oauth2.VanllaAuthToken;
import com.github.vanlla.shiro.adapter.VanllaRealmAdapter;
import com.github.vanlla.shiro.token.VanllaLoginToken;
import com.github.vanlla.shiro.util.LoginType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Set;


/**
 * 前端用户认证
 *
 * @author Vanlla
 * @since 1.0
 */
@Component
public class VanllaPortalRealm extends VanllaRealmAdapter {

    protected static Logger LOGGER = LoggerFactory.getLogger(VanllaPortalRealm.class);


    @Override
    public int getLoginType() {
        return LoginType.WORK_WECHAT;
    }

    @Override
    public VanllaLoginToken getVanllaLoginToken(VanllaAuthToken authToken) {
        return null;
    }

    @Override
    public Set<String> getStringPermissions(VanllaLoginToken loginToken) {
        return null;
    }
}
