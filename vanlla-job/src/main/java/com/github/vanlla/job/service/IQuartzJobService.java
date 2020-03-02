package com.github.vanlla.job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.vanlla.core.util.PageUtils;
import com.github.vanlla.job.entity.QuartzJobEntity;

import java.util.Map;

/**
 * 定时任务配置类
 *
 * @author Vanlla
 * @since 1.0
 */
public interface IQuartzJobService extends IService<QuartzJobEntity> {

    PageUtils search(Map<String, Object> params);
}

