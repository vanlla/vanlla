package com.github.vanlla.generator.service;

import cn.hutool.core.io.IoUtil;
import com.github.vanlla.generator.entity.system.GenInfo;
import com.github.vanlla.generator.mapper.SysGeneratorMapper;
import com.github.vanlla.generator.utils.GenSystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器
 *
 * @author Vanlla
 * @since 1.0
 */
@Service
public class SysGeneratorService {

    @Autowired
    private SysGeneratorMapper sysGeneratorMapper;

    public List<Map<String, Object>> queryList(Map<String, Object> map) {
        return sysGeneratorMapper.queryList(map);
    }

    public int queryTotal(Map<String, Object> map) {
        return sysGeneratorMapper.queryTotal(map);
    }

    public Map<String, String> queryTable(String tableName) {
        return sysGeneratorMapper.queryTable(tableName);
    }

    public List<Map<String, String>> queryColumns(String tableName) {
        return sysGeneratorMapper.queryColumns(tableName);
    }

    public byte[] generatorSystemCode(GenInfo genInfo) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        GenSystemUtils.generatorCode(genInfo, zip);

        IoUtil.close(zip);
        return outputStream.toByteArray();
    }

}
