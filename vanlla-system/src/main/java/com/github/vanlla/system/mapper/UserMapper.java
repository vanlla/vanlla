package com.github.vanlla.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.vanlla.system.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 用户实体
 *
 * @author Vanlla
 * @since 1.0
 */
public interface UserMapper extends BaseMapper<UserEntity> {

    IPage<UserEntity> search(Page<UserEntity> page, @Param("param") Map<String, Object> param);

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
