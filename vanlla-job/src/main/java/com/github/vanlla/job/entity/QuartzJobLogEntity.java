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
 * 定时任务日志
 *
 * @author Vanlla
 * @since 1.0
 */
@Data
@TableName("tb_quartz_job_log")
@ApiModel(description = "定时任务日志")
public class QuartzJobLogEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键", example = "1")
    private Integer id;
    /**
     * 任务ID
     */
    @ApiModelProperty(value = "任务ID", example = "1")
    private Integer jobId;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", example = "1")
    private Integer result;
    /**
     * 异常信息
     */
    @ApiModelProperty(value = "异常信息", example = "测试异常信息001")
    private String exception;
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间", example = "2018-08-08")
    private Date startTime;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间", example = "2018-08-08")
    private Date completeTime;

}
