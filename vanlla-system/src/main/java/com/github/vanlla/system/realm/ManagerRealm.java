package com.github.vanlla.system.realm;

import com.github.vanlla.shiro.util.LoginType;
import com.github.vanlla.shiro.adapter.VanllaRealmAdapter;
import com.github.vanlla.shiro.token.VanllaLoginToken;
import com.github.vanlla.shiro.oauth2.VanllaAuthToken;
import com.github.vanlla.system.service.IMenuService;
import com.github.vanlla.system.service.IUserTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


/**
 * 后台管理用户认证
 *
 * @author Vanlla
 * @since 1.0
 */
@Component
public class ManagerRealm extends VanllaRealmAdapter {

    protected static Logger LOGGER = LoggerFactory.getLogger(ManagerRealm.class);

    @Autowired
    private IUserTokenService userTokenService;

    @Autowired
    private IMenuService menuService;


    @Override
    public int getLoginType() {
        return LoginType.MANATER;
    }

    @Override
    public VanllaLoginToken getVanllaLoginToken(VanllaAuthToken authToken) {
        VanllaLoginToken loginToken = userTokenService.findByToken(authToken.getToken());
        return loginToken;
    }

    @Override
    public Set<String> getStringPermissions(VanllaLoginToken loginToken) {
        Set<String> permissions = new HashSet<>(menuService.findPermsByUserId(loginToken.getUserId()));
        return permissions;
    }
}
