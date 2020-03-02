package com.github.vanlla.job.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 企业微信群聊机器人
 *
 * @author Vanlla
 * @since 1.0
 */
@Data
@TableName("tb_qy_wechat_robot")
@ApiModel(description = "企业微信群聊机器人")
public class QyWechatRobotEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "ID", example = "1000001")
    private Long id;
    /**
     * 机器人名称
     */
    @ApiModelProperty(value = "机器人名称", example = "测试机器人名称001")
    private String name;
    /**
     * 内容
     */
    @ApiModelProperty(value = "内容", example = "测试内容001")
    private String content;
    /**
     * 机器人地址
     */
    @ApiModelProperty(value = "机器人地址", example = "测试机器人地址001")
    private String url;
    /**
     * 执行时间
     */
    @ApiModelProperty(value = "执行时间", example = "2018-08-08")
    private Date executeTime;

    /**
     * 是否启用 0：关闭，1：启用
     */
    private Integer enable;

}
