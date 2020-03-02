package com.github.vanlla.job.controller;

import com.github.vanlla.core.util.PageUtils;
import com.github.vanlla.core.web.BaseRestController;
import com.github.vanlla.job.entity.QuartzJobLogEntity;
import com.github.vanlla.job.service.IQuartzJobLogService;
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
 * 定时任务日志
 *
 * @author Vanlla
 * @since 1.0
 */
@Api(tags = "定时任务日志")
@RestController
@RequestMapping("/schedule/quartzJobLog")
public class QuartzJobLogController extends BaseRestController {
    @Autowired
    private IQuartzJobLogService quartzJobLogService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("schedule:quartzJobLog:list")
    @ApiOperation("查询定时任务日志列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "result", value = "状态", paramType = "query"),
            @ApiImplicitParam(name = "exception", value = "异常信息", paramType = "query"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", paramType = "query"),
            @ApiImplicitParam(name = "completeTime", value = "结束时间", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "页码", paramType = "query", required = true, defaultValue = "1"),
            @ApiImplicitParam(name = "limit", value = "每页条数", paramType = "query", required = true, defaultValue = "10"),
            @ApiImplicitParam(name = "sidx", value = "排序字段", paramType = "query"),
            @ApiImplicitParam(name = "order", value = "排序方式，如：asc、desc", paramType = "query")
    })
    public PageUtils<QuartzJobLogEntity> list(@RequestParam Map<String, Object> params) {
        PageUtils<QuartzJobLogEntity> page = quartzJobLogService.search(params);

        return page;
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("schedule:quartzJobLog:info")
    @ApiOperation("获取定时任务日志信息")
    @ApiImplicitParam(name = "id", value = "主键", paramType = "path", required = true)
    public QuartzJobLogEntity info(@PathVariable("id") Integer id) {
        QuartzJobLogEntity quartzJobLog = quartzJobLogService.getById(id);

        return quartzJobLog;
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("schedule:quartzJobLog:save")
    @ApiOperation("保存定时任务日志")
    public QuartzJobLogEntity save(@RequestBody QuartzJobLogEntity quartzJobLog) {
        quartzJobLogService.save(quartzJobLog);

        return quartzJobLog;
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @RequiresPermissions("schedule:quartzJobLog:update")
    @ApiOperation("修改定时任务日志")
    public QuartzJobLogEntity update(@RequestBody QuartzJobLogEntity quartzJobLog) {
        quartzJobLogService.updateById(quartzJobLog);

        return quartzJobLog;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("schedule:quartzJobLog:delete")
    @ApiOperation("删除定时任务日志")
    public void delete(@RequestBody Integer[] ids) {
        quartzJobLogService.removeByIds(Arrays.asList(ids));

    }

}
