package com.github.vanlla.job.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.vanlla.core.util.PageUtils;
import com.github.vanlla.core.util.Query;
import com.github.vanlla.job.entity.QuartzJobEntity;
import com.github.vanlla.job.mapper.QuartzJobMapper;
import com.github.vanlla.job.service.IQuartzJobService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * QuartzJobService
 *
 * @author Vanlla
 * @since 1.0
 */
@Service("quartzJobService")
public class QuartzJobServiceImpl extends ServiceImpl<QuartzJobMapper, QuartzJobEntity> implements IQuartzJobService {

    @Override
    public PageUtils search(Map<String, Object> params) {

        IPage<QuartzJobEntity> page = this.baseMapper.search(new Query<QuartzJobEntity>(params).getPage(), params);

        return new PageUtils(page);
    }

}
