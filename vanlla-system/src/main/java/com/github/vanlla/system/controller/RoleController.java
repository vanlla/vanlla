package com.github.vanlla.system.controller;

import com.github.vanlla.core.util.PageUtils;
import com.github.vanlla.core.web.BaseRestController;
import com.github.vanlla.system.entity.RoleEntity;
import com.github.vanlla.system.form.RoleForm;
import com.github.vanlla.system.service.IRoleMenuRefService;
import com.github.vanlla.system.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 角色实体
 *
 * @author Vanlla
 * @since 1.0
 */
@Api(tags = "角色实体")
@RestController
@RequestMapping("/system/role")
public class RoleController extends BaseRestController {
    @Autowired
    private IRoleService roleService;

    @Autowired
    private IRoleMenuRefService roleMenuRefService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("system:role:list")
    @ApiOperation("查询角色实体列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleName", value = "角色名称", paramType = "query"),
            @ApiImplicitParam(name = "roleDescription", value = "角色描述", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "状态", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "页码", paramType = "query", required = true, defaultValue = "1"),
            @ApiImplicitParam(name = "limit", value = "每页条数", paramType = "query", required = true, defaultValue = "10"),
            @ApiImplicitParam(name = "sidx", value = "排序字段", paramType = "query"),
            @ApiImplicitParam(name = "order", value = "排序方式，如：asc、desc", paramType = "query")
    })
    public PageUtils<RoleEntity> list( @RequestParam Map<String, Object> params) {
        PageUtils<RoleEntity> page = roleService.search(params);

        return page;
    }


    /**
     * 信息
     */
    @GetMapping("/info/{roleId}")
    @RequiresPermissions("system:role:info")
    @ApiOperation("获取角色实体信息")
    @ApiImplicitParam(name = "roleId", value = "角色ID", paramType = "path", required = true)
    public RoleForm info(@PathVariable("roleId") Long roleId) {
        RoleForm roleInfo = new RoleForm();
        RoleEntity role = roleService.getById(roleId);
        BeanUtils.copyProperties(role, roleInfo);
        roleInfo.setMenuIds(roleMenuRefService.findByRoleId(roleId));
        return roleInfo;
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("system:role:save")
    @ApiOperation("保存角色实体")
    public RoleEntity save(@RequestBody RoleForm roleForm) {

        RoleEntity role = new RoleEntity();
        BeanUtils.copyProperties(roleForm, role);

        //保存权限
        roleService.saveOrUpdateRole(role, roleForm.getMenuIds());


        return role;
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @RequiresPermissions("system:role:update")
    @ApiOperation("修改角色实体")
    public RoleEntity update(@RequestBody RoleForm roleForm) {
        RoleEntity role = new RoleEntity();
        BeanUtils.copyProperties(roleForm, role);

        //更新权限
        roleService.saveOrUpdateRole(role, roleForm.getMenuIds());
        return role;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("system:role:delete")
    @ApiOperation("删除角色实体")
    public void delete(@RequestBody Long[] roleIds) {
        roleService.delelteRole(roleIds);

    }


    @GetMapping("/listAll")
    @RequiresPermissions("system:role:listAll")
    @ApiOperation("获取角色列表")
    public List<RoleEntity> listAll() {
        return roleService.listAll();
    }

}
