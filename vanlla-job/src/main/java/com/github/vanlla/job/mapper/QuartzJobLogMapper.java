package com.github.vanlla.job.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.vanlla.job.entity.QuartzJobLogEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 定时任务日志
 *
 * @author Vanlla
 * @since 1.0
 */
public interface QuartzJobLogMapper extends BaseMapper<QuartzJobLogEntity> {

    IPage<QuartzJobLogEntity> search(Page<QuartzJobLogEntity> page, @Param("param") Map<String, Object> param);

}
