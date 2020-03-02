package com.github.vanlla.job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.vanlla.core.util.PageUtils;
import com.github.vanlla.job.entity.QuartzJobLogEntity;

import java.util.Map;

/**
 * 定时任务日志
 *
 * @author Vanlla
 * @since 1.0
 */
public interface IQuartzJobLogService extends IService<QuartzJobLogEntity> {

    PageUtils search(Map<String, Object> params);
}

