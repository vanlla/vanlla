package com.github.vanlla.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.vanlla.system.entity.RoleMenuRefEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色菜单关Mapper
 *
 * @author Vanlla
 * @since 0.1
 */
public interface RoleMenuRefMapper extends BaseMapper<RoleMenuRefEntity> {

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
