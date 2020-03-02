package com.github.vanlla.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.github.vanlla.core.util.PageUtils;
import com.github.vanlla.system.entity.CaptchaEntity;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * 验证码
 *
 * @author Vanlla
 * @since 1.0
 */
public interface ICaptchaService extends IService<CaptchaEntity> {

    PageUtils search(Map<String, Object> params);


    /**
     * 生产验证码
     *
     * @param uuid
     * @return
     */
    BufferedImage getCaptcha(String uuid);


    /**
     * 校验验证码
     *
     * @param uuid
     * @param code
     * @return
     */
    boolean validate(String uuid, String code);
}

