package com.github.vanlla.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.vanlla.system.entity.ExternalUserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 外部用户
 *
 * @author Vanlla
 * @since 1.0
 */
public interface ExternalUserMapper extends BaseMapper<ExternalUserEntity> {

    IPage<ExternalUserEntity> search(Page<ExternalUserEntity> page, @Param("param") Map<String, Object> param);

    /**
     * 查找外部用户信息
     *
     * @param userId
     * @param organization
     * @return
     */
    ExternalUserEntity findExternalUser(@Param("userId") String userId, @Param("organization") String organization);
}
