package com.kk.generator.entity;

import lombok.Data;

/**
 * @ProjectName: kk-code-generator
 * @Package: com.kk.generator.entity
 * @ClassName: TableCl
 * @Author: HK
 * @Description:
 * @Date: 2023/4/23 23:30
 * @Version: 1.0
 */
@Data
public class TableColumn {

    private String columnName;
    private String columnType;
    private String columnDefault;
    private String isNullable;
    private String columnComment;


    // constructor
    public TableColumn(String columnName, String columnType, String columnDefault, String isNullable, String columnComment) {
        this.columnName = columnName;
        this.columnType = columnType;
        this.columnDefault = columnDefault;
        this.isNullable = isNullable;
        this.columnComment = columnComment;
    }
}
