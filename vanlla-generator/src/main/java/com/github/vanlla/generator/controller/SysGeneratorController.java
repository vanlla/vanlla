package com.github.vanlla.generator.controller;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSON;
import com.github.vanlla.core.web.HttpRequestWrapper;
import com.github.vanlla.generator.entity.system.GenInfo;
import com.github.vanlla.generator.service.SysGeneratorService;
import com.github.vanlla.generator.utils.PageUtils;
import com.github.vanlla.generator.utils.Query;
import com.github.vanlla.generator.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 *
 * @author Vanlla
 * @since 1.0
 */
@Controller
@RequestMapping("/sys/generator")
public class SysGeneratorController {
    @Autowired
    private SysGeneratorService sysGeneratorService;

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<Map<String, Object>> list = sysGeneratorService.queryList(query);
        int total = sysGeneratorService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    @ResponseBody
    @RequestMapping("/{tableName}/columns")
    public R detail(@PathVariable("tableName") String tableName) {
        List columns = sysGeneratorService.queryColumns(tableName);
        return R.ok().put("columns", columns);
    }

    /**
     * 生成代码
     */
    @RequestMapping("/code/system")
    public void codeSystem(HttpRequestWrapper request, HttpServletResponse response) throws IOException {

        String genInfo = URLDecoder.decode(request.toString().split("=")[1], "utf-8");
        GenInfo gen = JSON.parseObject(genInfo, GenInfo.class);
        byte[] data = sysGeneratorService.generatorSystemCode(gen);
        System.out.println(123);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"modules.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IoUtil.write(response.getOutputStream(), false, data);
    }

}
