package com.greedystar.generator.entity;

import com.greedystar.generator.utils.StringUtil;

import java.io.Serializable;

/**
 * Author GreedyStar
 * Date   2018/4/19
 */
public class ColumnInfo implements Serializable {
    private String columnName; // 列名
    private int type; // 数据库数据类型代码
    private String propertyName; // 属性名
    private Boolean ifPrimaryKey; // 是否主键
    private String javaType;//java数据类型
    private String jdbcType;//java数据类型

    public ColumnInfo() {

    }

    public ColumnInfo(String columnName, int type, Boolean ifPrimaryKey,String jdbcType) {
        this.columnName = columnName;
        this.type = type;
        this.propertyName = StringUtil.columnName2PropertyName(columnName);
        this.ifPrimaryKey = ifPrimaryKey;
        this.jdbcType=jdbcType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }


    public Boolean getIfPrimaryKey() {
        return ifPrimaryKey;
    }

    public void setIfPrimaryKey(Boolean ifPrimaryKey) {
        this.ifPrimaryKey = ifPrimaryKey;
    }
}
