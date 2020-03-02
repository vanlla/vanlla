package com.github.vanlla.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 角色权限关联表
 *
 * @author Vanlla
 * @since 1.0
 */
@Data
@TableName("tb_role_menu_ref")
public class RoleMenuRefEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 权限ID
     */
    private Long menuId;

}
