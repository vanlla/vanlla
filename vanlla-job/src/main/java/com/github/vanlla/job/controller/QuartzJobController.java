package com.github.vanlla.job.controller;

import com.github.vanlla.core.util.PageUtils;
import com.github.vanlla.core.web.BaseRestController;
import com.github.vanlla.job.entity.QuartzJobEntity;
import com.github.vanlla.job.service.IQuartzJobService;
import com.github.vanlla.job.util.QuartzJobManager;
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
 * 定时任务配置类
 *
 * @author Ivan
 * @email shushenge@gmail.com
 */
@Api(tags = "定时任务配置类")
@RestController
@RequestMapping("/quartz/job")
public class QuartzJobController extends BaseRestController {
    @Autowired
    private IQuartzJobService quartzJobService;

    @Autowired
    private QuartzJobManager quartzJobManager;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("quartz:job:list")
    @ApiOperation("查询定时任务配置类列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "任务名称", paramType = "query"),
            @ApiImplicitParam(name = "description", value = "任务描述", paramType = "query"),
            @ApiImplicitParam(name = "ip", value = "绑定指定IP执行", paramType = "query"),
            @ApiImplicitParam(name = "className", value = "类名", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "状态", paramType = "query"),
            @ApiImplicitParam(name = "cronExpression", value = "表达式", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "页码", paramType = "query", required = true, defaultValue = "1"),
            @ApiImplicitParam(name = "limit", value = "每页条数", paramType = "query", required = true, defaultValue = "10"),
            @ApiImplicitParam(name = "sidx", value = "排序字段", paramType = "query"),
            @ApiImplicitParam(name = "order", value = "排序方式，如：asc、desc", paramType = "query")
    })
    public PageUtils<QuartzJobEntity> list(@RequestParam Map<String, Object> params) {
        PageUtils<QuartzJobEntity> page = quartzJobService.search(params);

        return page;
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("quartz:job:info")
    @ApiOperation("获取定时任务配置类信息")
    @ApiImplicitParam(name = "id", value = "主键", paramType = "path", required = true)
    public QuartzJobEntity info(@PathVariable("id") Integer id) {
        QuartzJobEntity quartzJob = quartzJobService.getById(id);

        return quartzJob;
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("quartz:job:save")
    @ApiOperation("保存定时任务配置类")
    public QuartzJobEntity save(@RequestBody QuartzJobEntity quartzJob) throws Exception {
        quartzJobService.save(quartzJob);
        quartzJobManager.addJob(quartzJob);
        return quartzJob;
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @RequiresPermissions("quartz:job:update")
    @ApiOperation("修改定时任务配置类")
    public QuartzJobEntity update(@RequestBody QuartzJobEntity quartzJob) throws Exception {
        quartzJobService.updateById(quartzJob);
        quartzJobManager.modifyJob(quartzJob);
        return quartzJob;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("quartz:job:delete")
    @ApiOperation("删除定时任务配置类")
    public void delete(@RequestBody Integer[] ids) throws Exception {

        if (ids != null) {
            for (Integer id : ids) {
                QuartzJobEntity job = quartzJobService.getById(id);
                quartzJobManager.deleteJob(job.getName());
            }
        }
        //删除任务
        quartzJobService.removeByIds(Arrays.asList(ids));
    }

    /**
     * 判断是否存在某个类
     *
     * @param className
     * @return
     */
    @GetMapping("/has")
    @RequiresPermissions("quartz:job:info")
    @ApiOperation("获取定时任务配置类信息")
    @ApiImplicitParam(name = "id", value = "主键", paramType = "path", required = true)
    public boolean hasClassName(@RequestParam("className") String className) {
        boolean has = true;
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            has = false;
        }

        return has;
    }


    /**
     * @param id
     * @param enable
     * @return
     */
    @GetMapping("/updateEnable")
    @RequiresPermissions("quartz:job:update")
    @ApiOperation("更新定时任务状态")
    public boolean updateEnable(@RequestParam("id") Integer id, @RequestParam("enable") Integer enable) throws Exception {
        QuartzJobEntity job = quartzJobService.getById(id);
        job.setEnable(enable);
        if (0 == enable) {
            quartzJobManager.deleteJob(job.getName());
        } else if (1 == enable) {
            quartzJobManager.addJob(job);
        }
        quartzJobService.saveOrUpdate(job);
        return true;
    }

}
