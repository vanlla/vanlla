package com.github.vanlla.system.form;


import lombok.Data;

import java.util.List;

@Data
public class UserForm {

    private Long userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 描述
     */
    private String description;
    /**
     * 状态
     */
    private String status;

    private String mobile;

    private String idCard;

    private String userType;

    private String salt;

    /**
     * 所属角色ID集合
     */
    private List<Long> roleIds;

}
