package com.github.vanlla.job.controller;

import com.github.vanlla.core.util.PageUtils;
import com.github.vanlla.core.web.BaseRestController;
import com.github.vanlla.job.entity.QyWechatRobotEntity;
import com.github.vanlla.job.service.IQyWechatRobotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * 企业微信群聊机器人
 *
 * @author Vanlla
 * @since 1.0
 */
@Api(tags = "企业微信群聊机器人管理")
@RestController
@RequestMapping("/schedule/robot")
public class QyWechatRobotController extends BaseRestController {
    @Autowired
    private IQyWechatRobotService qyWechatRobotService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("schedule:qyWechatRobot:list")
    @ApiOperation("查询企业微信群聊机器人列表")
    public PageUtils<QyWechatRobotEntity> list(@RequestParam(required = false) Map<String, Object> params) {
        PageUtils<QyWechatRobotEntity> page = qyWechatRobotService.search(params);
        return page;
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("schedule:qyWechatRobot:info")
    @ApiOperation("获取企业微信群聊机器人信息")
    @ApiImplicitParam(name = "id", value = "ID", paramType = "path", required = true)
    public QyWechatRobotEntity info(@PathVariable("id") Long id) {
        QyWechatRobotEntity qyWechatRobot = qyWechatRobotService.getById(id);

        return qyWechatRobot;
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("schedule:qyWechatRobot:save")
    @ApiOperation("保存企业微信群聊机器人")
    public QyWechatRobotEntity save(@RequestBody QyWechatRobotEntity qyWechatRobot) {
        qyWechatRobotService.save(qyWechatRobot);

        return qyWechatRobot;
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @RequiresPermissions("schedule:qyWechatRobot:update")
    @ApiOperation("修改企业微信群聊机器人")
    public QyWechatRobotEntity update(@RequestBody QyWechatRobotEntity qyWechatRobot) {
        qyWechatRobotService.updateById(qyWechatRobot);

        return qyWechatRobot;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("schedule:qyWechatRobot:delete")
    @ApiOperation("删除企业微信群聊机器人")
    public void delete(@RequestBody Long[] ids) {
        qyWechatRobotService.removeByIds(Arrays.asList(ids));

    }

}
