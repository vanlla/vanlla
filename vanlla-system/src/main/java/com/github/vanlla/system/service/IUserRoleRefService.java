package com.github.vanlla.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.vanlla.core.util.PageUtils;
import com.github.vanlla.system.entity.UserRoleRefEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户角色关联
 *
 * @author Vanlla
 * @since 1.0
 */
public interface IUserRoleRefService extends IService<UserRoleRefEntity> {

    PageUtils search(Map<String, Object> params);


    /**
     * 根据用户删除用户角色关系表
     *
     * @param userId
     * @return
     */
    boolean deleteByUserId(@Param("userId") String userId);


    /**
     * 获取用户所属角色集合
     *
     * @param userId
     * @return
     */
    List<String> getRoleByUserId(@Param("userId") String userId);
}

