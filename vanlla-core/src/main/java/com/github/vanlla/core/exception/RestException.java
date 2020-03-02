package com.github.vanlla.core.exception;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.github.vanlla.core.util.FastJsonUtils;
import com.github.vanlla.core.web.RestStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Restful 异常
 *
 * @author Vanlla
 * @since 1.0
 */
public class RestException extends RuntimeException {
    protected Logger LOGGER = LoggerFactory.getLogger(RestException.class);
    private Integer code = -2;
    private String msg;
    private String error;
    public static RestException UNAUTHORIZED = new RestException(-401, "非法操作，权限不足");
    public static RestException NOT_FOUND = new RestException(-404, "内容不存在");

    public RestException(Exception e) {
        super(e.getMessage());
        this.setError(e);
    }

    public RestException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public RestException(String msg, Exception e) {
        super(msg);
        this.msg = msg;
        this.setError(e);
    }

    public RestException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public RestException(Integer code, String msg, String error) {
        super(msg);
        this.code = code;
        this.msg = msg;
        this.error = error;
    }

    public RestException(Integer code, String msg, Exception e) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getError() {
        return this.error;
    }

    public Map<String, Object> asMap() {
        Map map = new HashMap();
        map.put("code", this.code);
        map.put("msg", this.msg);
        if (this.error != null) {
            map.put("error", this.error);
        }

        return map;
    }

    public String asString() {
        return FastJsonUtils.toString(this.asMap());
    }

    public boolean isError() {
        return this.code.equals(RestStatus.ERROR.code);
    }

    private void setError(Exception e) {
        this.LOGGER.error("系统异常", e);
        e.printStackTrace();
        this.code = RestStatus.ERROR.code;
        if (this.msg == null) {
            this.msg = RestStatus.ERROR.msg;
        }

        this.error = ExceptionUtil.stacktraceToString(e);
    }
}
