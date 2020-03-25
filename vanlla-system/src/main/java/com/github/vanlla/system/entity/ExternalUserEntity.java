package com.github.vanlla.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 外部用户
 *
 * @author Vanlla
 * @since 1.0
 */
@Data
@TableName("tb_external_user")
@ApiModel(description = "外部用户")
public class ExternalUserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "ID", example = "1000001")
    private String id;
    /**
     * 外键，用户表ID
     */
    @ApiModelProperty(value = "外键，用户表ID", example = "测试外键，用户表ID001")
    private String internalUserId;
    /**
     * 外部用户ID
     */
    @ApiModelProperty(value = "外部用户ID", example = "测试外部用户ID001")
    private String userId;
    /**
     * 外部用户名称
     */
    @ApiModelProperty(value = "外部用户名称", example = "测试外部用户名称001")
    private String userName;
    /**
     * 外部用户类型
     */
    @ApiModelProperty(value = "外部用户类型", example = "测试外部用户类型001")
    private String userType;
    /**
     * 外部用户组织信息
     */
    @ApiModelProperty(value = "外部用户组织信息", example = "测试外部用户组织信息001")
    private String organization;
    /**
     * 扩展字段1
     */
    @ApiModelProperty(value = "扩展字段1", example = "测试扩展字段1001")
    private String ext1;
    /**
     * 扩展字段2
     */
    @ApiModelProperty(value = "扩展字段2", example = "测试扩展字段2001")
    private String ext2;

}
