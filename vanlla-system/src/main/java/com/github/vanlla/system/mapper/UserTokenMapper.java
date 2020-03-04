package com.github.vanlla.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.vanlla.system.entity.UserTokenEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 用户实体
 *
 * @author Vanlla
 * @since 1.0
 */
public interface UserTokenMapper extends BaseMapper<UserTokenEntity> {

    IPage<UserTokenEntity> search(Page<UserTokenEntity> page, @Param("param") Map<String, Object> param);


    /**
     * 根据token查找用户
     *
     * @param token
     * @return
     */
    UserTokenEntity findByToken(@Param("token") String token);


    /**
     * 查找是否有存在的token记录
     *
     * @param userId
     * @param ext1
     * @param loginType
     * @return
     */
    UserTokenEntity findToken(@Param("userId") String userId, @Param("loginType") Integer loginType, @Param("ext1") String ext1);

}
