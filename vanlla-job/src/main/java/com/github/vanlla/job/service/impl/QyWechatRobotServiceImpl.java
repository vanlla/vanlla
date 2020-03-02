package com.github.vanlla.job.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.vanlla.core.util.PageUtils;
import com.github.vanlla.core.util.Query;
import com.github.vanlla.job.entity.QyWechatRobotEntity;
import com.github.vanlla.job.mapper.QyWechatRobotMapper;
import com.github.vanlla.job.service.IQyWechatRobotService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 实现类
 *
 * @author Vanlla
 * @since 1.0
 */
@Service("qyWechatRobotService")
public class QyWechatRobotServiceImpl extends ServiceImpl<QyWechatRobotMapper, QyWechatRobotEntity> implements IQyWechatRobotService {

    @Override
    public PageUtils search(Map<String, Object> params) {

        IPage<QyWechatRobotEntity> page = this.baseMapper.search(new Query<QyWechatRobotEntity>(params).getPage(), params);

        return new PageUtils(page);
    }

}
