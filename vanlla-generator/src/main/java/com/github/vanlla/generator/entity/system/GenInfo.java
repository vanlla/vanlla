package com.github.vanlla.generator.entity.system;

import java.io.Serializable;
import java.util.List;

/**
 * GenInfo
 *
 * @author Vanlla
 * @since 1.0
 */
public class GenInfo implements Serializable {

    public TableInfo table;

    public List<ColumnInfo> columnList;

    public TableInfo getTable() {
        return table;
    }

    public void setTable(TableInfo table) {
        this.table = table;
    }

    public List<ColumnInfo> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<ColumnInfo> columnList) {
        this.columnList = columnList;
    }
}
