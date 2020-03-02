package com.github.vanlla.job.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.vanlla.core.util.PageUtils;
import com.github.vanlla.core.util.Query;
import com.github.vanlla.job.entity.QuartzJobLogEntity;
import com.github.vanlla.job.mapper.QuartzJobLogMapper;
import com.github.vanlla.job.service.IQuartzJobLogService;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * QuartzJobLogService
 *
 * @author Vanlla
 * @since 1.0
 */
@Service("quartzJobLogService")
public class QuartzJobLogServiceImpl extends ServiceImpl<QuartzJobLogMapper, QuartzJobLogEntity> implements IQuartzJobLogService {

    @Override
    public PageUtils search(Map<String, Object> params) {

        IPage<QuartzJobLogEntity> page = this.baseMapper.search(new Query<QuartzJobLogEntity>(params).getPage(), params);

        return new PageUtils(page);
    }

}
