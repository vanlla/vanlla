package com.github.vanlla.core.web;

import cn.hutool.core.util.ArrayUtil;
import com.github.vanlla.core.exception.RestException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Base Restful Controller
 *
 * @author Vanlla
 * @since 1.0
 */
public abstract class BaseRestController extends BaseController {
    public BaseRestController() {
    }

    @ExceptionHandler({RestException.class})
    public RestException processApiException(RestException e) {
        return e;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public RestException processApiException(MethodArgumentNotValidException e) {
        return getRestException(e.getBindingResult().getAllErrors().iterator());
    }

    @ExceptionHandler({BindException.class})
    public RestException doBindException(BindException e) {
        return getRestException(e.getBindingResult().getAllErrors().iterator());
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public RestException doHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        String msg = e.getMessage();
        if (msg.startsWith("JSON parse error")) {
            String start = "field : ";
            String end = ";";
            msg = "字段[" + msg.substring(msg.indexOf(start) + start.length(), msg.indexOf(end)) + "]解析出错";
        }

        return new RestException(msg, e);
    }

    @ExceptionHandler({Exception.class})
    public RestException processException(Exception e) {
        return new RestException(e);
    }

    private RestException getRestException(Iterator iterator) {

        List<String> msgList = new ArrayList<>();
        while (iterator.hasNext()) {
            ObjectError objectError = (ObjectError) iterator.next();
            msgList.add(objectError.getDefaultMessage());
        }
        return new RestException(ArrayUtil.toString(msgList));
    }
}