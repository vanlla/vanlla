package com.github.vanlla.generator.entity.system;

/**
 * AssertInfo
 *
 * @author Vanlla
 * @since 1.0
 */
public class AssertInfo {

    GenInfo genInfo;
    boolean hasBigDecimal;
    boolean hasDate;
    boolean hasBigDecimalInSearch;
    boolean hasDateInSearch;

    public AssertInfo(GenInfo genInfo) {
        this.genInfo = genInfo;
        for (ColumnInfo col : genInfo.getColumnList()) {
            if (col.getJavaType().equals("BigDecimal")) {
                hasBigDecimal = true;
                if (col.getWhere().equals("true")) {
                    hasBigDecimalInSearch = true;
                }
            }
            if (col.getJavaType().equals("Date")) {
                hasDate = true;
                if (col.getWhere().equals("true")) {
                    hasDateInSearch = true;
                }
            }
        }
    }

    public boolean getHasBigDecimal() {
        return hasBigDecimal;
    }

    public boolean getHasDate() {
        return hasDate;
    }

    public boolean getHasBigDecimalInSearch() {
        return hasBigDecimalInSearch;
    }

    public boolean getHasDateInSearch() {
        return hasDateInSearch;
    }

}
