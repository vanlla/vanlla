package com.github.vanlla.system.form;


import lombok.Data;

/**
 * 登录表单
 */
@Data
public class LoginForm {

    private String userName;

    private String password;

    private String uuid;

    /**
     * 验证码
     */
    private String code;

}
