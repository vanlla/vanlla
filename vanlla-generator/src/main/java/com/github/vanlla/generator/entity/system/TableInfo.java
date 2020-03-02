package com.github.vanlla.generator.entity.system;

import java.io.Serializable;
import java.util.List;

/**
 * TableInfo
 *
 * @author Vanlla
 * @since 1.0
 */
public class TableInfo implements Serializable {

    public String tableName;
    public String pkgName;
    public String moduleName;
    public String clsName;
    public String comments;
    public String pk;
    public String del;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public String getClsName() {
        return clsName;
    }

    public String getClsname() {
        return clsName.substring(0, 1).toLowerCase() + clsName.substring(1);
    }

    public void setClsName(String clsName) {
        this.clsName = clsName;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getPathName() {
        String pn = getClsname().replaceFirst(moduleName, "");
        return pn.substring(0, 1).toLowerCase() + pn.substring(1);
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public ColumnInfo getPkEntity(List<ColumnInfo> columns) {
        for (ColumnInfo col : columns) {
            if (col.getColumnName().equals(getPk())) {
                return col;
            }
        }
        return null;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }

    public ColumnInfo getDelEntity(List<ColumnInfo> columns) {
        for (ColumnInfo col : columns) {
            if (col.getColumnName().equals(getDel())) {
                return col;
            }
        }
        return null;
    }
}
