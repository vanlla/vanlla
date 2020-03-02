package com.github.vanlla.system.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.vanlla.core.exception.RestException;
import com.github.vanlla.core.util.PageUtils;
import com.github.vanlla.core.util.Query;
import com.github.vanlla.system.entity.CaptchaEntity;
import com.github.vanlla.system.mapper.CaptchaMapper;
import com.github.vanlla.system.service.ICaptchaService;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.Calendar;
import java.util.Map;

/**
 * 验证码Service
 *
 * @author Vanlla
 * @since 1.0
 */
@Service("captchaService")
public class CaptchaServiceImpl extends ServiceImpl<CaptchaMapper, CaptchaEntity> implements ICaptchaService {

    @Autowired
    private Producer producer;

    @Override
    public PageUtils search(Map<String, Object> params) {

        IPage<CaptchaEntity> page = this.baseMapper.search(new Query<CaptchaEntity>(params).getPage(), params);

        return new PageUtils<>(page);
    }


    @Override
    public BufferedImage getCaptcha(String uuid) {
        if (StrUtil.isBlank(uuid)) {
            throw new RestException("uuid不能为空");
        } else {
            String code = this.producer.createText();
            CaptchaEntity captchaEntity = new CaptchaEntity();
            captchaEntity.setUuid(uuid);
            captchaEntity.setCode(code);
            captchaEntity.setExpireTime(DateUtil.offsetMinute(Calendar.getInstance().getTime(), 5));
            this.saveOrUpdate(captchaEntity);
            return this.producer.createImage(code);
        }
    }

    @Override
    public boolean validate(String uuid, String code) {
        CaptchaEntity captchaEntity = this.getById(uuid);
        if (captchaEntity == null) {
            return false;
        } else {
            this.removeById(uuid);
            return captchaEntity.getCode().equalsIgnoreCase(code) && captchaEntity.getExpireTime().getTime() >= System.currentTimeMillis();
        }
    }
}
