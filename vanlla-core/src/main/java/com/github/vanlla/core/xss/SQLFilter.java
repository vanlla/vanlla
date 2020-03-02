package com.github.vanlla.core.xss;

import cn.hutool.core.util.StrUtil;
import com.github.vanlla.core.exception.RestException;


/**
 * Xss SQL Filter
 *
 * @author Vanlla
 * @since 1.0
 */
public class SQLFilter {
    public SQLFilter() {
    }

    public static String sqlInject(String str) {
        if (StrUtil.isBlank(str)) {
            return null;
        } else {
            str = StrUtil.replace(str, "'", "");
            str = StrUtil.replace(str, "\"", "");
            str = StrUtil.replace(str, ";", "");
            str = StrUtil.replace(str, "\\", "");
            str = str.toLowerCase();
            String[] keywords = new String[]{"master", "truncate", "insert", "select", "delete", "update", "declare", "alert", "drop"};
            String[] var2 = keywords;
            int var3 = keywords.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                String keyword = var2[var4];
                if (str.indexOf(keyword) != -1) {
                    throw new RestException("包含非法字符");
                }
            }

            return str;
        }
    }
}