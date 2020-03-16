package com.github.vanlla.system.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体
 *
 * @author Vanlla
 * @since 1.0
 */
@Data
@TableName("tb_user")
@ApiModel(description = "用户实体")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "ID", example = "1000001")
    private String userId;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", example = "测试001")
    private String userName;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", example = "测试001")
    private String password;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 身份证号码
     */
    private String idCard;

    /**
     * 状态（0 禁用，1正常）
     */
    private String status;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreated;

    /**
     * 更新时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;

    /**
     * 创建人
     */
    private String gmtAuthor;

    /**
     * 盐值加密
     */
    private String salt;

    /**
     * 账号类别（0 : 内部用户，1 : 外部用户）
     */
    private String userType;

    /**
     * 最后一次登录时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;

    /**
     * 最后一次登录IP
     */
    private String lastLoginIp;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", example = "测试001")
    private String description;

}
