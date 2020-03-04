package com.github.vanlla.system.entity;


import com.alibaba.fastjson.annotation.JSONField;
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

    @JSONField(serialize = false)
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 用户ID
     */
    @JSONField(serialize = false)
    private String userId;

    /**
     * 生成的Token
     */
    private String token;

    /**
     * 过期时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date expireTime;

    /**
     * 更新时间
     */
    @JSONField(serialize = false)
    private Date updateTime;

    /**
     * 用户类型 0:后台管理用户，1：Work Wechat用户，需要后面扩展
     */
    @JSONField(serialize = false)
    private Integer type;

    /**
     * 扩展字段1
     */
    @JSONField(serialize = false)
    private String ext1;

    /**
     * 扩展字段2
     */
    @JSONField(serialize = false)
    private String ext2;


}
