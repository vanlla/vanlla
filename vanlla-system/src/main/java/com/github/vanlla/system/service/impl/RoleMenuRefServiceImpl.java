package com.github.vanlla.system.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.vanlla.system.entity.RoleMenuRefEntity;
import com.github.vanlla.system.mapper.RoleMenuRefMapper;
import com.github.vanlla.system.service.IRoleMenuRefService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色菜单实现类
 *
 * @author Vanlla
 * @since 1.0
 */
@Service("roleMenuRefService")
public class RoleMenuRefServiceImpl extends ServiceImpl<RoleMenuRefMapper, RoleMenuRefEntity> implements IRoleMenuRefService {

    @Override
    public boolean deleteByRoleId(String roleId) {
        return baseMapper.deleteByRoleId(roleId);
    }

    @Override
    public List<String> findByRoleId(String roleId) {
        return baseMapper.findByRoleId(roleId);
    }
}
