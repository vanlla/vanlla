package com.github.vanlla.system.controller;

import com.github.vanlla.core.util.PageUtils;
import com.github.vanlla.core.web.BaseRestController;
import com.github.vanlla.system.entity.MenuEntity;
import com.github.vanlla.system.service.IMenuService;
import com.github.vanlla.shiro.util.ShiroUtils;
import com.github.vanlla.system.vo.MenuNode;
import com.github.vanlla.system.vo.MenuVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 菜单管理
 *
 * @author Vanlla
 * @since 1.0
 */
@Api(tags = "菜单管理")
@RestController
@RequestMapping("/system/menu")
public class MenuController extends BaseRestController {
    @Autowired
    private IMenuService menuService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("system:menu:list")
    @ApiOperation("查询菜单管理列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "名称", paramType = "query"),
            @ApiImplicitParam(name = "url", value = "菜单URL", paramType = "query"),
            @ApiImplicitParam(name = "perms", value = "授权", paramType = "query"),
            @ApiImplicitParam(name = "icon", value = "菜单图标", paramType = "query"),
            @ApiImplicitParam(name = "isLeaf", value = "1为叶子节点，0不是叶子节点", paramType = "query"),
            @ApiImplicitParam(name = "orderNo", value = "排序", paramType = "query"),
            @ApiImplicitParam(name = "rwAccess", value = "读写权限 0为只读，1为读写", paramType = "query"),
            @ApiImplicitParam(name = "isSysmenu", value = "是否系统菜单，0为系统菜单，1或空为业务菜单", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "页码", paramType = "query", required = true, defaultValue = "1"),
            @ApiImplicitParam(name = "limit", value = "每页条数", paramType = "query", required = true, defaultValue = "10"),
            @ApiImplicitParam(name = "sidx", value = "排序字段", paramType = "query"),
            @ApiImplicitParam(name = "order", value = "排序方式，如：asc、desc", paramType = "query")
    })
    public PageUtils<MenuEntity> list(@RequestParam Map<String, Object> params) {
        PageUtils<MenuEntity> page = menuService.search(params);

        return page;
    }


    /**
     * 信息
     */
    @GetMapping("/info/{menuId}")
    @RequiresPermissions("system:menu:info")
    @ApiOperation("获取菜单管理信息")
    @ApiImplicitParam(name = "menuId", value = "菜单编号", paramType = "path", required = true)
    public MenuVO info(@PathVariable("menuId") Long menuId) {
        return menuService.findById(menuId);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("system:menu:save")
    @ApiOperation("保存菜单管理")
    public MenuEntity save(@RequestBody MenuEntity menu) {

        menuService.save(menu);

        return menu;
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @RequiresPermissions("system:menu:update")
    @ApiOperation("修改菜单管理")
    public MenuEntity update(@RequestBody MenuEntity menu) {
        menuService.updateById(menu);

        return menu;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("system:menu:delete")
    @ApiOperation("删除菜单管理")
    public void delete(@RequestBody Long[] menuIds) {
        menuService.removeByIds(Arrays.asList(menuIds));

    }


    /**
     * 信息
     */
    @GetMapping("/type/{type}")
    @RequiresPermissions("system:menu:list")
    public List<MenuNode> type(@PathVariable("type") Integer type) {

        return menuService.findByType(type);
    }

    /**
     * 信息
     */
    @GetMapping("/parent/{parentId}")
    @RequiresPermissions("system:menu:list")
    public List<MenuNode> parent(@PathVariable("parentId") Long parentId) {

        return menuService.findByParentId(parentId);
    }

    /**
     * 获取树形结构
     */
    @GetMapping("/tree")
    @RequiresPermissions("system:menu:list")
    public List<MenuNode> getTree() {

        return menuService.getTree();
    }


    /**
     * 获取用户菜单
     */
    @PutMapping("/menu")
    @RequiresPermissions("system:user:login")
    @ApiOperation("获取用户菜单")
    public List<MenuNode> getMenuList() {
        return menuService.getMenuByUserId(ShiroUtils.getUserId());
    }


}
