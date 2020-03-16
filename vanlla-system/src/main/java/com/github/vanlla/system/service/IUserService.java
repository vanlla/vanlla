package com.github.vanlla.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.vanlla.core.util.PageUtils;
import com.github.vanlla.system.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户Service
 *
 * @author Vanlla
 * @since 1.0
 */
public interface IUserService extends IService<UserEntity> {

    PageUtils search(Map<String, Object> params);

    /**
     * 新增或更新用户
     *
     * @param user
     * @param roleIds
     * @return
     */
    boolean saveOrUpdateUser(UserEntity user, List<String> roleIds);

    /**
     * 根据用户名称查找用户
     *
     * @param userName
     * @return
     */
    UserEntity findByUserName(@Param("userName") String userName);

    /**
     * 根据用户Id查找
     *
     * @param userId
     * @return
     */
    UserEntity findByUserId(@Param("userId") String userId);
}

