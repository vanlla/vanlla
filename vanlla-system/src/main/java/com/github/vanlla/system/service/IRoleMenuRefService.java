package com.github.vanlla.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.vanlla.system.entity.RoleMenuRefEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色菜单接口
 *
 * @author Vanlla
 * @since 1.0
 */
public interface IRoleMenuRefService extends IService<RoleMenuRefEntity> {

    /**
     * 删除该角色关联的权限
     *
     * @param roleId 角色ID
     * @return
     */
    boolean deleteByRoleId(@Param("roleId") Long roleId);

    /**
     * 通过角色查找权限
     *
     * @param roleId
     * @return
     */
    List<Long> findByRoleId(@Param("roleId") Long roleId);

}
