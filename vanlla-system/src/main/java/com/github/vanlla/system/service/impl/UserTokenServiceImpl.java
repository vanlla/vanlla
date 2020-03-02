package com.github.vanlla.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.vanlla.core.util.PageUtils;
import com.github.vanlla.core.util.Query;
import com.github.vanlla.system.entity.UserTokenEntity;
import com.github.vanlla.system.mapper.UserTokenMapper;
import com.github.vanlla.system.service.IUserTokenService;
import org.springframework.stereotype.Service;

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
}
