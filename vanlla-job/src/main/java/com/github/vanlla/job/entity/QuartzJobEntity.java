package com.github.vanlla.job.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 定时任务配置类
 *
 * @author Vanlla
 * @since 1.0
 */
@Data
@TableName("tb_quartz_job")
@ApiModel(description = "定时任务配置类")
public class QuartzJobEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键", example = "1")
    private Integer id;
    /**
     * 任务名称
     */
    @ApiModelProperty(value = "任务名称", example = "测试任务名称001")
    private String name;
    /**
     * 任务描述
     */
    @ApiModelProperty(value = "任务描述", example = "测试任务描述001")
    private String description;
    /**
     * 绑定指定IP执行
     */
    @ApiModelProperty(value = "绑定指定IP执行", example = "测试绑定指定IP执行001")
    private String ip;
    /**
     * 类名
     */
    @ApiModelProperty(value = "类名", example = "测试类名001")
    private String className;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", example = "1")
    private Integer status;
    /**
     * 表达式
     */
    @ApiModelProperty(value = "表达式", example = "测试表达式001")
    private String cronExpression;

    /**
     * 是否启用
     */
    private Integer enable;

}
