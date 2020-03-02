package com.github.vanlla.job.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.vanlla.job.entity.QyWechatRobotEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 企业微信群聊机器人
 *
 * @author Vanlla
 * @since 1.0
 */
public interface QyWechatRobotMapper extends BaseMapper<QyWechatRobotEntity> {

    IPage<QyWechatRobotEntity> search(Page<QyWechatRobotEntity> page, @Param("param") Map<String, Object> param);

}
