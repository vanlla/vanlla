package com.github.vanlla.job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.vanlla.core.util.PageUtils;
import com.github.vanlla.job.entity.QyWechatRobotEntity;

import java.util.Map;

/**
 * 企业微信群聊机器人
 *
 * @author Vanlla
 * @since 1.0
 */
public interface IQyWechatRobotService extends IService<QyWechatRobotEntity> {

    PageUtils search(Map<String, Object> params);
}

