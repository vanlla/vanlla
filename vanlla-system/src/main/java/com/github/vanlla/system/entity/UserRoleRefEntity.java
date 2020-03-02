package com.github.vanlla.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户角色关联
 *
 * @author Vanlla
 * @since 1.0
 */
@Data
@TableName("tb_user_role_ref")
@ApiModel(description = "用户角色关联")
public class UserRoleRefEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "ID", example = "1000001")
    private Long id;
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID", example = "测试001")
    private Long userId;
    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID", example = "测试001")
    private Long roleId;


}
