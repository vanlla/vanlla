package com.github.vanlla.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.vanlla.core.util.PageUtils;
import com.github.vanlla.system.entity.RoleEntity;

import java.util.List;
import java.util.Map;

/**
 * 角色实体
 *
 * @author Vanlla
 * @since 1.0
 */
public interface IRoleService extends IService<RoleEntity> {

    PageUtils search(Map<String, Object> params);

    /**
     * 保存或更新角色
     *
     * @param role
     * @param menuIds
     * @return
     */
    boolean saveOrUpdateRole(RoleEntity role, List<Long> menuIds);

    /**
     * 删除角色
     *
     * @param roleId
     */
    void delelteRole(Long[] roleId);

    /**
     * 获取所有的角色
     *
     * @return
     */
    List<RoleEntity> listAll();
}

