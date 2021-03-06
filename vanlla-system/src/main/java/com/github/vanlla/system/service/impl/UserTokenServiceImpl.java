package com.github.vanlla.system.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.vanlla.core.util.PageUtils;
import com.github.vanlla.core.util.Query;
import com.github.vanlla.shiro.oauth2.TokenGenerator;
import com.github.vanlla.system.entity.UserTokenEntity;
import com.github.vanlla.system.mapper.UserTokenMapper;
import com.github.vanlla.system.service.IUserTokenService;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Map;


/**
 * 用户Token Service
 *
 * @author Vanlla
 * @since 1.0
 */
@Service("userTokenService")
public class UserTokenServiceImpl extends ServiceImpl<UserTokenMapper, UserTokenEntity> implements IUserTokenService {


    @Override
    public PageUtils search(Map<String, Object> params) {

        IPage<UserTokenEntity> page = this.baseMapper.search(new Query<UserTokenEntity>(params).getPage(), params);

        return new PageUtils<>(page);
    }


    @Override
    public UserTokenEntity findByToken(String token) {
        return this.baseMapper.findByToken(token);
    }

    @Override
    public UserTokenEntity refreshToken(String userId, Integer loginType, String ext1) {
        UserTokenEntity tokenEntity = this.baseMapper.findToken(userId, loginType, ext1);
        tokenEntity = tokenEntity == null ? new UserTokenEntity() : tokenEntity;
        //设置token
        tokenEntity.setToken(TokenGenerator.generateValue(loginType));
        tokenEntity.setExpireTime(DateUtil.offsetHour(Calendar.getInstance().getTime(), 2));
        tokenEntity.setUserId(userId);
        tokenEntity.setType(loginType);
        tokenEntity.setExt1(ext1);
        //更新到库里面
        this.saveOrUpdate(tokenEntity);
        //输出到前端去掉userId和id属性
        return tokenEntity;
    }
}
