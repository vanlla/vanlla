package com.github.vanlla.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.vanlla.system.entity.CaptchaEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 验证码
 *
 * @author Vanlla
 * @since 1.0
 */
public interface CaptchaMapper extends BaseMapper<CaptchaEntity> {

    IPage<CaptchaEntity> search(Page<CaptchaEntity> page, @Param("param") Map<String, Object> param);

}
