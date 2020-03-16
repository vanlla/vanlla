package com.github.vanlla.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.vanlla.core.util.PageUtils;
import com.github.vanlla.core.util.Query;
import com.github.vanlla.system.entity.UserRoleRefEntity;
import com.github.vanlla.system.mapper.UserRoleRefMapper;
import com.github.vanlla.system.service.IUserRoleRefService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 用户角色关联Service
 *
 * @author Vanlla
 * @since 1.0
 */
@Service("userRoleRefService")
public class UserRoleRefServiceImpl extends ServiceImpl<UserRoleRefMapper, UserRoleRefEntity> implements IUserRoleRefService {

    @Override
    public PageUtils search(Map<String, Object> params) {

        IPage<UserRoleRefEntity> page = this.baseMapper.search(new Query<UserRoleRefEntity>(params).getPage(), params);

        return new PageUtils<>(page);
    }


    @Override
    public boolean deleteByUserId(String userId) {
        return baseMapper.deleteByUserId(userId);
    }


    @Override
    public List<String> getRoleByUserId(String userId) {
        return baseMapper.getRoleByUserId(userId);
    }
}
