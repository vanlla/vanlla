package com.github.vanlla.shiro.autoconfigure;

import cn.hutool.core.util.StrUtil;
import com.github.vanlla.core.exception.RestException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 权限异常处理器
 *
 * @author Vanlla
 * @since 1.0
 */
@RestControllerAdvice
public class ShiroExceptionHandler {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public ShiroExceptionHandler() {
    }

    @ExceptionHandler({DuplicateKeyException.class})
    public RestException handleDuplicateKeyException(DuplicateKeyException e) {
        this.LOGGER.error(e.getMessage(), e);
        return new RestException("数据库中已存在该记录");
    }

    @ExceptionHandler({AuthorizationException.class})
    public RestException handleAuthorizationException(AuthorizationException e) {
        this.LOGGER.error(e.getMessage(), e);
        return new RestException("没有权限，请联系管理员授权");
    }

    @ExceptionHandler({Exception.class})
    public RestException handleException(Exception e) {
        this.LOGGER.error(e.getMessage(), e);
        return new RestException("没有权限，请联系管理员授权");
    }

    @ExceptionHandler({UnauthorizedException.class})
    public RestException doUnauthorizedException(UnauthorizedException e) {
        String msg = "当前用户权限不足";
        String eMsg = e.getMessage();
        int i1 = StrUtil.indexOf(eMsg, '[');
        int i2 = StrUtil.indexOf(eMsg, ']');
        if (i1 > -1 && i2 > -1) {
            msg = "当前用户缺少" + eMsg.substring(i1) + "权限";
        }

        return new RestException(401, msg);
    }
}