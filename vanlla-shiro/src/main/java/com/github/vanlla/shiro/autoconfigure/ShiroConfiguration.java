package com.github.vanlla.shiro.autoconfigure;

import com.github.vanlla.shiro.adapter.ShiroFilterPathAdapter;
import com.github.vanlla.shiro.oauth2.VanllaAuthFilter;
import com.github.vanlla.shiro.oauth2.VanllaModularRealmAuthenticator;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置类
 *
 * @author Vanlla
 * @since 1.0
 */
@Configuration
public class ShiroConfiguration {


    @Bean
    public ShiroFilterFactoryBean shiroFilter(ShiroFilterPathAdapter adapter, SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        Map<String, Filter> filters = new HashMap();
        filters.put("oauth2", new VanllaAuthFilter());
        shiroFilter.setFilters(filters);

        Map<String, String> filterMap = new LinkedHashMap();

        //不需要权限的路径
        String[] anonConfig = adapter.getAnonConfig();
        for (int i = 0; i < anonConfig.length; ++i) {
            filterMap.put(anonConfig[i], "anon");
        }

        //需要 OAuth2Filter处理的路径
        String[] oAuth2Config = adapter.getOAuth2Config();
        for (int i = 0; i < oAuth2Config.length; ++i) {
            filterMap.put(oAuth2Config[i], "oauth2");
        }

        shiroFilter.setFilterChainDefinitionMap(filterMap);
        return shiroFilter;
    }


    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdCookieEnabled(true);
        return sessionManager;
    }


    @Bean
    public SecurityManager securityManager(AuthorizingRealm... oAuth2Realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setAuthenticator(modularRealmAuthenticator());
        securityManager.setRealms(Arrays.asList(oAuth2Realm));
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    /**
     * 系统自带的Realm管理，主要针对多realm 认证
     */
    @Bean
    public ModularRealmAuthenticator modularRealmAuthenticator() {
        //自己重写的ModularRealmAuthenticator
        VanllaModularRealmAuthenticator modularRealmAuthenticator = new VanllaModularRealmAuthenticator();
        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return modularRealmAuthenticator;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

   /* @Bean
    public FilterRegistrationBean shiroFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        registration.addInitParameter("targetFilterLifecycle", "true");
        registration.setEnabled(true);
        registration.setOrder(2147483646);
        registration.addUrlPatterns(new String[]{"/*"});
        return registration;
    }*/

}
