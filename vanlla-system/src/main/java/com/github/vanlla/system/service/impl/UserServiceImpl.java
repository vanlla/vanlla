package com.github.vanlla.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.vanlla.core.util.PageUtils;
import com.github.vanlla.core.util.Query;
import com.github.vanlla.system.entity.UserEntity;
import com.github.vanlla.system.entity.UserRoleRefEntity;
import com.github.vanlla.system.mapper.UserMapper;
import com.github.vanlla.system.service.IUserRoleRefService;
import com.github.vanlla.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 用户Service
 *
 * @author Vanlla
 * @since 1.0
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {

    @Autowired
    private IUserRoleRefService userRoleRefService;

    @Override
    public PageUtils search(Map<String, Object> params) {

        IPage<UserEntity> page = this.baseMapper.search(new Query<UserEntity>(params).getPage(), params);

        return new PageUtils<>(page);
    }


    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean saveOrUpdateUser(UserEntity user, List<Long> roleIds) {

        //保存用户关系
        this.saveOrUpdate(user);
        //删除旧的用户角色关系
        userRoleRefService.deleteByUserId(user.getUserId());
        //新增用户角色关系
        addUserRoleRefs(user.getUserId(), roleIds);

        return true;
    }

    private void addUserRoleRefs(Long userId, List<Long> roleIds) {
        if (ObjectUtil.isEmpty(roleIds)) {
            return;
        }

        UserRoleRefEntity ref = null;
        List<UserRoleRefEntity> refs = new ArrayList<>();
        for (Long roleId : roleIds) {
            ref = new UserRoleRefEntity();
            ref.setRoleId(roleId);
            ref.setUserId(userId);
            refs.add(ref);
        }

        userRoleRefService.saveBatch(refs);
    }


    @Override
    public UserEntity findByUserName(String userName) {
        return baseMapper.findByUserName(userName);
    }

    @Override
    public UserEntity findByUserId(Long userId) {
        return baseMapper.findByUserId(userId);
    }
}
