package com.github.vanlla.generator.mapper;

import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 *
 * @author Vanlla
 * @since 1.0
 */
public interface SysGeneratorMapper {

    List<Map<String, Object>> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    Map<String, String> queryTable(String tableName);

    List<Map<String, String>> queryColumns(String tableName);
}
