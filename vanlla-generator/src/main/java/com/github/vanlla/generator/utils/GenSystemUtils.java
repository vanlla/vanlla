package com.github.vanlla.generator.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import com.github.vanlla.generator.entity.system.AssertInfo;
import com.github.vanlla.generator.entity.system.ColumnInfo;
import com.github.vanlla.generator.entity.system.GenInfo;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器   工具类
 *
 * @author Vanlla
 * @since 1.0
 */
public class GenSystemUtils {

    public static List<String> getTemplates() {
        List<String> templates = new ArrayList<String>();
        templates.add("template/system/Entity.java.vm");
        templates.add("template/system/Mapper.java.vm");
        templates.add("template/system/Mapper.xml.vm");
        templates.add("template/system/IService.java.vm");
        templates.add("template/system/ServiceImpl.java.vm");
        templates.add("template/system/Controller.java.vm");

        templates.add("template/system/menu.sql.vm");

        templates.add("template/system/elementui/index.vue.vm");
//        templates.add("template/system/elementui/add-or-update.vue.vm");
        templates.add("template/system/elementui/index.js.vm");
        templates.add("template/system/elementui/api.js.vm");
        templates.add("template/system/elementui/add.vue.vm");
        templates.add("template/system/elementui/detail.vue.vm");

        return templates;
    }

    /**
     * 生成代码
     */
    public static void generatorCode(GenInfo genInfo, ZipOutputStream zip) {
        //配置信息
        Configuration config = getConfig();

        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);
//        String mainPath = config.getString("mainPath" );
//        mainPath = StringUtils.isBlank(mainPath) ? "cn.com.do1.generator" : mainPath;

        if (genInfo.getColumnList().size() > 0) {
            genInfo.getColumnList().get(genInfo.getColumnList().size() - 1).setLastOne(true);
        }
        //封装模板数据
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", genInfo.getTable().getTableName());
        map.put("comments", genInfo.getTable().getComments());
        map.put("pk", genInfo.getTable().getPkEntity(genInfo.getColumnList()));
        map.put("className", genInfo.getTable().getClsName());
        map.put("classname", genInfo.getTable().getClsname());
        map.put("pathName", genInfo.getTable().getPathName());
        map.put("columns", genInfo.getColumnList());
        map.put("mainPath", "com.github.vanlla");
        map.put("package", genInfo.getTable().getPkgName());
        map.put("moduleName", genInfo.getTable().getModuleName());
        map.put("author", config.getString("author"));
        map.put("email", config.getString("email"));
        map.put("datetime", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        map.put("assertInfo", new AssertInfo(genInfo));

        ColumnInfo searchColumn = null; //搜索关键字
        for (ColumnInfo columnInfo : genInfo.getColumnList()) {
            if ("true".equals(columnInfo.getWhere())) {
                searchColumn = columnInfo;
                break;
            }
        }
        map.put("searchColumn", searchColumn);

        VelocityContext context = new VelocityContext(map);

        //获取模板列表
        List<String> templates = getTemplates();
        for (String template : templates) {
            //渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            tpl.merge(context, sw);

            try {
                //添加到zip genInfo.getTable().getPkgName()
                zip.putNextEntry(new ZipEntry(getFileName(template, genInfo.getTable().getClsName(), genInfo.getTable().getPkgName(), genInfo.getTable().getModuleName())));
                IoUtil.writeUtf8(zip, false, sw.toString());
                IoUtil.close(sw);
                zip.closeEntry();
            } catch (IOException e) {
                throw new RRException("渲染模板失败，表名：" + genInfo.getTable().getTableName(), e);
            }
        }
    }


    /**
     * 列名转换成Java属性名
     */
    public static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
    }

    /**
     * 表名转换成Java类名
     */
    public static String tableToJava(String tableName, String tablePrefix) {
        if (StringUtils.isNotBlank(tablePrefix)) {
            tableName = tableName.replace(tablePrefix, "");
        }
        return columnToJava(tableName);
    }

    /**
     * 获取配置信息
     */
    public static Configuration getConfig() {
        try {
            return new PropertiesConfiguration("generator.properties");
        } catch (Exception e) {
            throw new RRException("获取配置文件失败，", e);
        }
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template, String className, String packageName, String moduleName) {
        String classname = className.substring(0, 1).toLowerCase() + className.substring(1);

        String packagePath = "main" + File.separator + "java" + File.separator;
        if (StringUtils.isNotBlank(packageName)) {
            packagePath += packageName.replace(".", File.separator) + File.separator + moduleName + File.separator;
        }

        if (template.contains("Entity.java.vm")) {
            return "backend" + File.separator + "web" + File.separator + "src" + File.separator + packagePath + "entity" + File.separator + className + "Entity.java";
        }

        if (template.contains("Mapper.java.vm")) {
            return "backend" + File.separator + "web" + File.separator + "src" + File.separator + packagePath + "mapper" + File.separator + className + "Mapper.java";
        }

        if (template.contains("IService.java.vm")) {
            return "backend" + File.separator + "web" + File.separator + "src" + File.separator + packagePath + "service" + File.separator + "I" + className + "Service.java";
        }

        if (template.contains("ServiceImpl.java.vm")) {
            return "backend" + File.separator + "web" + File.separator + "src" + File.separator + packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
        }

        if (template.contains("Controller.java.vm")) {
            return "backend" + File.separator + "web" + File.separator + "src" + File.separator + packagePath + "controller" + File.separator + className + "Controller.java";
        }

        if (template.contains("Mapper.xml.vm")) {
            return "backend" + File.separator + "web" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "mapper" + File.separator + moduleName + File.separator + className + "Mapper.xml";
        }

        if (template.contains("menu.sql.vm")) {
            return "backend" + File.separator + className.toLowerCase() + "_menu.sql";
        }

        if (template.contains("index.vue.vm")) {
            return "frontend" + File.separator + "src" + File.separator + "views" + File.separator + "admin" + File.separator + "systemManage" +
                    File.separator + className.toLowerCase() + File.separator + "index.vue";
        }

        if (template.contains("add.vue.vm")) {
            return "frontend" + File.separator + "src" + File.separator + "views" + File.separator + "admin" + File.separator + "systemManage" +
                    File.separator + className.toLowerCase() + File.separator + "add" + className + ".vue";
        }

        if (template.contains("detail.vue.vm")) {
            return "frontend" + File.separator + "src" + File.separator + "views" + File.separator + "admin" + File.separator + "systemManage" +
                    File.separator + className.toLowerCase() + File.separator + classname + "Detail.vue";
        }

        if (template.contains("index.js.vm")) {
            return "frontend" + File.separator + "index.js";
        }

        if (template.contains("api.js.vm")) {
            return "frontend" + File.separator + "api.js";
        }

        return null;
    }
}
