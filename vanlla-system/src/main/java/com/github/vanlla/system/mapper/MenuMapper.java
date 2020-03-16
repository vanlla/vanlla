package com.github.vanlla.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.vanlla.system.entity.MenuEntity;
import com.github.vanlla.system.vo.MenuNode;
import com.github.vanlla.system.vo.MenuVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 菜单管理
 *
 * @author Vanlla
 * @since 1.0
 */
public interface MenuMapper extends BaseMapper<MenuEntity> {

    IPage<MenuEntity> search(Page<MenuEntity> page, @Param("param") Map<String, Object> param);

    /**
     * 根据类型查找
     *
     * @param type
     * @return
     */
    List<MenuNode> findByType(@Param("type") Integer type);

    /**
     * 根据父ID 查找
     *
     * @param parentId
     * @return
     */
    List<MenuNode> findByParentId(@Param("parentId") String parentId);

    /**
     * 查找菜单详情
     *
     * @param id
     * @return
     */
    MenuVO findMenuInfoById(@Param("id") String id);

    /**
     * 查找用户的菜单
     *
     * @param id
     * @return
     */
    List<MenuEntity> findMenuByUserId(@Param("id") String id);

    /**
     * 查找用户的权限
     *
     * @param id
     * @return
     */
    List<String> findPermsByUserId(@Param("id") String id);

}
