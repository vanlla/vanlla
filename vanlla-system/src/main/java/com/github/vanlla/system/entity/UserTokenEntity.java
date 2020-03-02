package com.github.vanlla.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.vanlla.shiro.token.VanllaLoginToken;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录Token实体
 *
 * @author Vanlla
 * @since 1.0
 */
@Data
@TableName("tb_user_token")
public class UserTokenEntity implements VanllaLoginToken, Serializable {

    @TableId(type = IdType.INPUT)
    private String id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 生成的Token
     */
    private String token;

    /**
     * 过期时间
     */
    private Date expireTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 用户类型 0:后台管理用户，1：Work Wechat用户
     */
    private Integer type;

    /**
     * 设置ID
     */
    public void setId() {
        this.id = this.type + "_" + this.getUserId();
    }

}
