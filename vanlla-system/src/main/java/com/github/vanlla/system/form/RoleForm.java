package com.github.vanlla.system.form;


import lombok.Data;

import java.util.List;

@Data
public class RoleForm {

    private String roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色描述
     */
    private String roleDescription;
    /**
     * 状态
     */
    private Integer status;

    /**
     * 权限列表
     */
    private List<String> menuIds;
}
