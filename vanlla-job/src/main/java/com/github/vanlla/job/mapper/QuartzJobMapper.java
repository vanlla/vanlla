package com.github.vanlla.job.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.vanlla.job.entity.QuartzJobEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 定时任务配置类
 *
 * @author Vanlla
 * @since 1.0
 */
public interface QuartzJobMapper extends BaseMapper<QuartzJobEntity> {

    IPage<QuartzJobEntity> search(Page<QuartzJobEntity> page, @Param("param") Map<String, Object> param);

}
