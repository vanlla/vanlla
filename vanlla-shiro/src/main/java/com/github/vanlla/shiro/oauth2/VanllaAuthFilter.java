package com.github.vanlla.shiro.oauth2;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.vanlla.core.exception.RestException;
import com.github.vanlla.core.util.HttpContextUtils;
import com.github.vanlla.core.web.RestStatus;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Shiro auth 过滤器
 *
 * @author Vanlla
 * @since 1.0
 */
public class VanllaAuthFilter extends AuthenticatingFilter {

    protected static Logger LOGGER = LoggerFactory.getLogger(VanllaAuthFilter.class);


    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {

        return getRequestToken((HttpServletRequest) request);
    }

    /**
     * 没有token需要重新认证
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        VanllaAuthToken authToken = this.getRequestToken((HttpServletRequest) request);
        return !ObjectUtil.isEmpty(authToken) && !StrUtil.isBlank(authToken.getToken()) && super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        VanllaAuthToken authToken = this.getRequestToken((HttpServletRequest) request);
        if (ObjectUtil.isEmpty(authToken) || StrUtil.isBlank(authToken.getToken())) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setContentType("application/json;charset=utf-8");
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
            String json = RestStatus.TOKEN_INVALID.asString();
            httpResponse.getWriter().print(json);
            return false;
        } else {
            return this.executeLogin(request, response);
        }
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());

        try {
            Throwable throwable = e.getCause() == null ? e : e.getCause();
            int code = -1;
            if (throwable instanceof RestException && throwable != null && ((RestException) throwable).getCode() != null && ((RestException) throwable).getCode() != 0) {
                code = ((RestException) throwable).getCode();
            }

            String json = (new RestException(code, throwable.getMessage())).asString();
            httpResponse.getWriter().print(json);
        } catch (IOException exception) {
            LOGGER.error("登录失败", e);
        }

        return false;
    }

    /**
     * 获取请求头中携带的token
     *
     * @param request
     * @return
     */
    private VanllaAuthToken getRequestToken(HttpServletRequest request) {

        String authorization = request.getHeader("Authorization");
        if (StrUtil.isBlank(authorization)) {
            authorization = request.getParameter("Authorization");
            if (StrUtil.isBlank(authorization) && request.getAttribute("Authorization") != null) {
                authorization = request.getAttribute("Authorization").toString();
            }
        }

        return StrUtil.isBlank(authorization) ? null : new VanllaAuthToken(authorization);
    }
}
