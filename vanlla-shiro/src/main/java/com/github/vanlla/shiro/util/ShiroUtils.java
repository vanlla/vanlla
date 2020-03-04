package com.github.vanlla.shiro.util;

import com.github.vanlla.core.exception.RestException;
import com.github.vanlla.shiro.token.VanllaLoginToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * shiro工具类
 *
 * @author Vanlla
 * @since 1.0
 */
public class ShiroUtils {

    public ShiroUtils() {

    }

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static VanllaLoginToken getLoginToken() {
        return (VanllaLoginToken) SecurityUtils.getSubject().getPrincipal();
    }

    public static String getUserId() {
        return getLoginToken().getUserId();
    }

    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    public static String getKaptcha(String key) {
        Object kaptcha = getSessionAttribute(key);
        if (kaptcha == null) {
            throw new RestException("验证码已失效");
        } else {
            getSession().removeAttribute(key);
            return kaptcha.toString();
        }
    }
}
