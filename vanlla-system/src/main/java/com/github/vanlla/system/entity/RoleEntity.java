package com.github.vanlla.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色实体
 *
 * @author Vanlla
 * @since 1.0
 */
@Data
@TableName("tb_role")
@ApiModel(description = "角色实体")
public class RoleEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "角色ID", example = "1000001")
    private String roleId;
    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称", example = "测试角色名称001")
    private String roleName;
    /**
     * 角色描述
     */
    @ApiModelProperty(value = "角色描述", example = "测试角色描述001")
    private String roleDescription;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", example = "1")
    private Integer status;


}
