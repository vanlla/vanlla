package com.github.vanlla.generator.entity.system;

import java.io.Serializable;
import java.util.Map;

/**
 * ColumnInfo
 *
 * @author Vanlla
 * @since 1.0
 */
public class ColumnInfo implements Serializable {

    public Integer numericPrecision;
    public String dataType;
    public String extra;
    public String nullAble;
    public String columnComment;
    public String columnKey;
    public Integer numericScale;
    public String columnName;
    public String dataTypeWithNum;
    public String javaType;
    public Integer maxLength;
    public String example;
    public String insert;
    public String update;
    public String where;
    public String whereType;
    public String[] rules;
    public Map disableds;
    public String javaName;
    public Boolean lastOne = false;

    public Integer getNumericPrecision() {
        return numericPrecision;
    }

    public void setNumericPrecision(Integer numericPrecision) {
        this.numericPrecision = numericPrecision;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getNullAble() {
        return nullAble;
    }

    public void setNullAble(String nullAble) {
        this.nullAble = nullAble;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public Integer getNumericScale() {
        return numericScale;
    }

    public void setNumericScale(Integer numericScale) {
        this.numericScale = numericScale;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataTypeWithNum() {
        return dataTypeWithNum;
    }

    public void setDataTypeWithNum(String dataTypeWithNum) {
        this.dataTypeWithNum = dataTypeWithNum;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getInsert() {
        return insert;
    }

    public void setInsert(String insert) {
        this.insert = insert;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String[] getRules() {
        return rules;
    }

    public void setRules(String[] rules) {
        this.rules = rules;
    }

    public Map getDisableds() {
        return disableds;
    }

    public void setDisableds(Map disableds) {
        this.disableds = disableds;
    }

    public String getJavaName() {
        return javaName;
    }

    public void setJavaName(String javaName) {
        this.javaName = javaName;
    }

    public String getComments() {
        return this.columnComment;
    }

    public String getAttrType() {
        return this.javaType;
    }

    public String getAttrname() {
        return this.javaName;
    }

    public String getAttrName() {
        return this.javaName.substring(0, 1).toUpperCase() + this.javaName.substring(1);
    }

    public String getWhereType() {
        return whereType;
    }

    public void setWhereType(String whereType) {
        this.whereType = whereType;
    }

    public boolean getHasRules() {
        return rules.length > 0;
    }

    public Boolean getLastOne() {
        return lastOne;
    }

    public void setLastOne(Boolean lastOne) {
        this.lastOne = lastOne;
    }

    public Boolean getIsNumber() {
        return "Integer".equals(getJavaType()) || "Long".equals(getJavaType()) || "BigDecimal".equals(getJavaType());
    }

}
