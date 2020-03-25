package com.github.vanlla.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.vanlla.core.util.PageUtils;
import com.github.vanlla.system.entity.ExternalUserEntity;
import com.github.vanlla.system.entity.UserEntity;

import java.util.List;
import java.util.Map;

/**
 * 外部用户
 *
 * @author Vanlla
 * @since 1.0
 */
public interface IExternalUserService extends IService<ExternalUserEntity> {

    PageUtils search(Map<String, Object> params);

    /**
     * 查找外部用户信息
     *
     * @param userId
     * @param organization
     * @return
     */
    ExternalUserEntity findExternalUser(String userId, String organization);


    /**
     * 通过外部单点用户获取到对应的管理员
     *
     * @param externalUser
     * @param roleIds
     * @return
     */
    UserEntity getUserEntity(ExternalUserEntity externalUser, List<String> roleIds);

}

