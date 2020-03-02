package com.github.vanlla.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.vanlla.core.util.PageUtils;
import com.github.vanlla.system.entity.UserTokenEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 用户Token实体
 *
 * @author Vanlla
 * @since 1.0
 */
public interface IUserTokenService extends IService<UserTokenEntity> {

    PageUtils search(Map<String, Object> params);

    /**
     * 根据token查找用户
     *
     * @param token
     * @return
     */
    UserTokenEntity findByToken(@Param("token") String token);

}

