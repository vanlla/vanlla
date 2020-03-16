package com.github.vanlla.system.controller;

import cn.hutool.core.util.RandomUtil;
import com.github.vanlla.core.exception.RestException;
import com.github.vanlla.core.util.PageUtils;
import com.github.vanlla.core.web.BaseRestController;
import com.github.vanlla.shiro.util.Sha256Hash;
import com.github.vanlla.system.entity.UserEntity;
import com.github.vanlla.system.form.UserForm;
import com.github.vanlla.system.service.IUserRoleRefService;
import com.github.vanlla.system.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * 用户Controller
 *
 * @author Vanlla
 * @since 1.0
 */
@Api(tags = "用户实体")
@RestController
@RequestMapping("/system/user")
public class UserController extends BaseRestController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRoleRefService userRoleRefService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("system:user:list")
    @ApiOperation("查询用户实体列表")
    public PageUtils<UserEntity> list(@RequestParam(required = false) Map<String, Object> params) {
        PageUtils<UserEntity> page = userService.search(params);

        return page;
    }


    /**
     * 信息
     */
    @GetMapping("/info/{userId}")
    @RequiresPermissions("system:user:info")
    @ApiOperation("获取用户实体信息")
    @ApiImplicitParam(name = "userId", value = "ID", paramType = "path", required = true)
    public UserForm info(@PathVariable("userId") String userId) {
        UserForm result = new UserForm();
        UserEntity user = userService.findByUserId(userId);
        BeanUtils.copyProperties(user, result);
        result.setRoleIds(userRoleRefService.getRoleByUserId(userId));
        return result;
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("system:user:save")
    @ApiOperation("保存用户实体")
    public UserForm save(@RequestBody UserForm user) throws Exception {

        UserEntity existUser = userService.findByUserName(user.getUserName());
        if (existUser != null) {
            throw new RestException(501, "当前用户名已存在");
        }

        Date now = Calendar.getInstance().getTime();
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        userEntity.setGmtAuthor("default");
        userEntity.setGmtCreated(now);
        userEntity.setGmtModified(now);
        userEntity.setSalt(RandomUtil.randomString(8));
        userEntity.setPassword((new Sha256Hash(userEntity.getPassword(), userEntity.getSalt())).toHex());
        userService.saveOrUpdateUser(userEntity, user.getRoleIds());
        return user;
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @RequiresPermissions("system:user:update")
    @ApiOperation("修改用户实体")
    public UserForm update(@RequestBody UserForm user) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        //防止被修改，设置为null
        userEntity.setUserName(null);
        userService.saveOrUpdateUser(userEntity, user.getRoleIds());
        return user;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("system:user:delete")
    @ApiOperation("删除用户实体")
    public void delete(@RequestBody String[] userIds) {
        userService.removeByIds(Arrays.asList(userIds));

    }

    /**
     * 修改
     */
    @PutMapping("/updatePassword")
    @RequiresPermissions("system:user:updateByAdmin")
    @ApiOperation("修改用户实体")
    public boolean updatePassword(@RequestBody Map<String, String> param) {
        String userId = param.get("userId");
        String newPassword = param.get("newPassword");
        UserEntity user = userService.getById(userId);

        //旧密码正确，更新新密码
        user.setPassword((new Sha256Hash(newPassword, user.getSalt())).toHex());
        userService.saveOrUpdate(user);

        return true;
    }

    /**
     * 修改
     */
    @PutMapping("/updatePasswordByUser")
    @RequiresPermissions("system:user:update")
    @ApiOperation("修改用户实体")
    public boolean updatePasswordByUser(@RequestBody Map<String, String> param) {
        String userId = param.get("userId");
        String oldPassword = param.get("oldPassword");
        String newPassword = param.get("newPassword");
        UserEntity user = userService.getById(userId);

        if (!user.getPassword().equals((new Sha256Hash(oldPassword, user.getSalt())).toHex())) {
            throw new RestException(501, "旧密码不正确");
        }

        //旧密码正确，更新新密码
        user.setPassword((new Sha256Hash(newPassword, user.getSalt())).toHex());
        userService.saveOrUpdate(user);

        return true;
    }


}
