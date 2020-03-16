package com.github.vanlla.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.vanlla.core.util.PageUtils;
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
public interface IMenuService extends IService<MenuEntity> {

    PageUtils search(Map<String, Object> params);

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
     * 根据父ID查找
     *
     * @param id
     * @return
     */
    MenuVO findById(String id);


    /**
     * 获取树形结构数据
     *
     * @return
     */
    List<MenuNode> getTree();

    /**
     * 获取用户菜单信息
     *
     * @param userId
     * @return
     */
    List<MenuNode> getMenuByUserId(String userId);


    /**
     * 查找用户的权限
     *
     * @param id
     * @return
     */
    List<String> findPermsByUserId(@Param("id") String id);
}

