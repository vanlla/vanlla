package com.github.vanlla.system.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.vanlla.core.util.PageUtils;
import com.github.vanlla.core.util.Query;
import com.github.vanlla.system.entity.RoleEntity;
import com.github.vanlla.system.entity.RoleMenuRefEntity;
import com.github.vanlla.system.mapper.RoleMapper;
import com.github.vanlla.system.service.IRoleMenuRefService;
import com.github.vanlla.system.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 角色管理Service
 *
 * @author Vanlla
 * @since 1.0
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements IRoleService {

    @Autowired
    private IRoleMenuRefService roleMenuRefService;


    @Override
    public PageUtils search(Map<String, Object> params) {

        IPage<RoleEntity> page = this.baseMapper.search(new Query<RoleEntity>(params).getPage(), params);

        return new PageUtils<>(page);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean saveOrUpdateRole(RoleEntity role, List<Long> menuIds) {

        //保存角色
        this.saveOrUpdate(role);
        //删除该角色原有的权限关系
        roleMenuRefService.deleteByRoleId(role.getRoleId());
        //建立新的角色权限管理
        addRoleMenuRefs(role.getRoleId(), menuIds);

        return true;
    }


    private void addRoleMenuRefs(Long roleId, List<Long> menuIds) {

        if (ObjectUtil.isEmpty(menuIds)) {
            return;
        }

        RoleMenuRefEntity ref = null;
        List<RoleMenuRefEntity> refs = new ArrayList<>();
        for (Long menuId : menuIds) {
            ref = new RoleMenuRefEntity();
            ref.setMenuId(menuId);
            ref.setRoleId(roleId);
            refs.add(ref);
        }

        roleMenuRefService.saveBatch(refs);

    }

    @Override
    public void delelteRole(Long[] roleIds) {
        if (ArrayUtil.isEmpty(roleIds)) {
            return;
        }

        for (Long roleId : roleIds) {
            this.removeById(roleId);
            roleMenuRefService.deleteByRoleId(roleId);
        }
    }

    @Override
    public List<RoleEntity> listAll() {
        return this.baseMapper.listAll();
    }
}
