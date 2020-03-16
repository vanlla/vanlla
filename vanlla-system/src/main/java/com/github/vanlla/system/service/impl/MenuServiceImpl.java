package com.github.vanlla.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.vanlla.core.util.PageUtils;
import com.github.vanlla.core.util.Query;
import com.github.vanlla.system.entity.MenuEntity;
import com.github.vanlla.system.mapper.MenuMapper;
import com.github.vanlla.system.service.IMenuService;
import com.github.vanlla.system.vo.MenuNode;
import com.github.vanlla.system.vo.MenuVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Menu Service
 *
 * @author Vanlla
 * @since 1.0q
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuEntity> implements IMenuService {

    @Override
    public PageUtils search(Map<String, Object> params) {

        IPage<MenuEntity> page = this.baseMapper.search(new Query<MenuEntity>(params).getPage(), params);

        return new PageUtils<>(page);
    }


    @Override
    public List<MenuNode> findByParentId(String parentId) {
        return baseMapper.findByParentId(parentId);
    }


    @Override
    public List<MenuNode> findByType(Integer type) {
        return baseMapper.findByType(type);
    }

    @Override
    public MenuVO findById(String id) {
        return baseMapper.findMenuInfoById(id);
    }


    @Override
    public List<MenuNode> getTree() {
        return this.getTreeMenuNode("0");
    }

    private List<MenuNode> getTreeMenuNode(String parentId) {

        List<MenuNode> menuNodes = baseMapper.findByParentId(parentId);
        if (!ObjectUtil.isEmpty(menuNodes)) {
            for (MenuNode menuNode : menuNodes) {
                menuNode.setChildren(this.getTreeMenuNode(menuNode.getId()));
            }
        }
        return menuNodes;
    }


    @Override
    public List<MenuNode> getMenuByUserId(String userId) {
        List<MenuEntity> menuList = this.baseMapper.findMenuByUserId(userId);
        return this.getMenuNode(menuList, "0");
    }

    private List<MenuNode> getMenuNode(List<MenuEntity> menuList, String parentId) {

        List<MenuNode> menuNodes = new ArrayList<>();
        if (!ObjectUtil.isEmpty(menuList)) {
            for (MenuEntity entity : menuList) {
                if (parentId.equals(entity.getParentId())) {
                    MenuNode menuNode = new MenuNode();
                    menuNode.setId(entity.getMenuId());
                    menuNode.setName(entity.getName());
                    menuNode.setTitle(entity.getTitle());
                    menuNode.setIcon(entity.getIcon());
                    menuNode.setUrl(entity.getUrl());
                    menuNodes.add(menuNode);
                }
            }
        }

        for (MenuNode menuNode : menuNodes) {
            menuNode.setChildren(this.getMenuNode(menuList, menuNode.getId()));
        }

        return menuNodes;

    }


    @Override
    public List<String> findPermsByUserId(String id) {
        return baseMapper.findPermsByUserId(id);
    }
}
