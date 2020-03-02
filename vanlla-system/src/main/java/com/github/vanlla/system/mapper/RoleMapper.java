package com.github.vanlla.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.vanlla.system.entity.RoleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 角色实体
 *
 * @author Vanlla
 * @since 1.0
 */
public interface RoleMapper extends BaseMapper<RoleEntity> {

    IPage<RoleEntity> search(Page<RoleEntity> page, @Param("param") Map<String, Object> param);

    /**
     * 获取所有的角色
     *
     * @return
     */
    List<RoleEntity> listAll();

}
