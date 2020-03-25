package com.github.vanlla.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.vanlla.core.util.PageUtils;
import com.github.vanlla.core.util.Query;
import com.github.vanlla.system.entity.ExternalUserEntity;
import com.github.vanlla.system.entity.UserEntity;
import com.github.vanlla.system.mapper.ExternalUserMapper;
import com.github.vanlla.system.service.IExternalUserService;
import com.github.vanlla.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * 实现类
 *
 * @author Vanlla
 * @since 1.0
 */
@Service("externalUserService")
public class ExternalUserServiceImpl extends ServiceImpl<ExternalUserMapper, ExternalUserEntity> implements IExternalUserService {

    @Autowired
    private IUserService userService;

    @Override
    public PageUtils search(Map<String, Object> params) {

        IPage<ExternalUserEntity> page = this.baseMapper.search(new Query<ExternalUserEntity>(params).getPage(), params);

        return new PageUtils(page);
    }


    @Override
    public ExternalUserEntity findExternalUser(String userId, String organization) {
        return this.baseMapper.findExternalUser(userId, organization);
    }

    @Override
    @Transactional
    public UserEntity getUserEntity(ExternalUserEntity externalUser, List<String> roleIds) {

        //查看是否存在该外部管理员
        ExternalUserEntity existUser = this.findExternalUser(externalUser.getUserId(), externalUser.getOrganization());
        UserEntity userEntity;
        if (ObjectUtil.isEmpty(existUser)) {
            //创建内部用户
            userEntity = new UserEntity();
            userEntity.setUserName(getUniqueUserName(externalUser.getUserId()));
            userEntity.setUserType("1");
            userEntity.setStatus("1");
            userEntity.setPassword("");
            userEntity.setGmtAuthor("");
            userService.saveOrUpdateUser(userEntity, roleIds);
            externalUser.setInternalUserId(userEntity.getUserId());
            //保存外部用户信息
            this.baseMapper.insert(externalUser);
        } else {
            userEntity = userService.findByUserId(existUser.getInternalUserId());
        }

        return userEntity;
    }

    /**
     * 得到唯一的userName
     *
     * @param userName
     * @return
     */
    private String getUniqueUserName(String userName) {
        if (ObjectUtil.isEmpty(userService.findByUserName(userName))) {
            return userName;
        } else {
            return getUniqueUserName(userName + "A");
        }
    }

}
