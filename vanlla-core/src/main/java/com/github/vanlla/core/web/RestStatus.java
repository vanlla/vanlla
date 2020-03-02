package com.github.vanlla.core.web;


import com.github.vanlla.core.util.FastJsonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 常用错误
 *
 * @author Vanlla
 * @since 1.0
 */
public enum RestStatus {

    SUCCESS(0),
    ERROR(-1),
    UNAUTHORIZED(-401, "未授权访问"),
    TOKEN_INVALID(-401, "Token无效"),
    FORBIDDEN(-403, "禁止访问"),
    NOT_FOUND(-404, "内容不存在"),
    SERVER_ERROR(-500, "服务器异常");

    public Integer code;
    public String msg;

    RestStatus(Integer code) {
        this.code = code;
        switch (code) {
            case -1:
                this.msg = "系统繁忙，请稍后再试";
                break;
            case 0:
                this.msg = "执行成功";
                break;
            default:
                this.msg = "系统未定义";
        }

    }

    RestStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Map<String, Object> asMap() {
        Map map = new HashMap();
        map.put("code", this.code);
        map.put("msg", this.msg);
        return map;
    }

    public String asString() {
        return FastJsonUtils.toString(this.asMap());
    }
}
