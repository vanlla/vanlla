package com.github.vanlla.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 验证码
 *
 * @author Vanlla
 * @since 1.0
 */
@Data
@TableName("tb_captcha")
@ApiModel(description = "验证码")
public class CaptchaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(type = IdType.INPUT)
    @ApiModelProperty(value = "ID", example = "1000001")
    private String uuid;
    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码", example = "测试001")
    private String code;
    /**
     * 过期时间
     */
    @ApiModelProperty(value = "过期时间", example = "2018-08-08")
    private Date expireTime;

}
