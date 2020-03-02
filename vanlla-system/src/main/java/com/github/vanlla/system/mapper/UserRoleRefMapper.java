package com.github.vanlla.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public interface UserRoleRefMapper extends BaseMapper<UserRoleRefEntity> {

    IPage<UserRoleRefEntity> search(Page<UserRoleRefEntity> page, @Param("param") Map<String, Object> param);

    /**
     * 根据用户删除用户角色关系表
     *
     * @param userId
     * @return
     */
    boolean deleteByUserId(@Param("userId") Long userId);

    /**
     * 获取用户所属角色集合
     *
     * @param userId
     * @return
     */
    List<Long> getRoleByUserId(@Param("userId") Long userId);

}
