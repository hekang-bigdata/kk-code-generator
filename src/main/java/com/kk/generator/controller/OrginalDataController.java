package com.kk.generator.controller;

import com.kk.generator.common.DataResult;
import com.kk.generator.entity.TableColumn;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: kk-code-generator
 * @Package: com.kk.generator
 * @ClassName: OrginalDataController
 * @Author: HK
 * @Description:
 * @Date: 2023/4/23 22:48
 * @Version: 1.0
 */
@RestController
@RequestMapping("orginalData")
public class OrginalDataController {
    //查询数据库
    @PostMapping("listDatabase")
    public DataResult listDatabase(@RequestBody Map<String, Object> map) throws Exception {
        JdbcTemplate jdbcTemplate = getJdbcTemplate();
        List<String> showDatabases = jdbcTemplate.queryForList("SHOW DATABASES", String.class);


        return DataResult.success(showDatabases);
    }

    //获得数据库下面的表
    @PostMapping("listTable")
    public DataResult lsitTable(@RequestBody Map<String, Object> map) throws Exception {

        String databaseName = String.valueOf(map.getOrDefault("databaseName", ""));
        JdbcTemplate jdbcTemplate = getJdbcTemplate();
        jdbcTemplate.execute("use " + databaseName);
        List<String> showTables = jdbcTemplate.queryForList("SHOW TABLES", String.class);


        return DataResult.success(showTables);
    }

    //获得表详情
    @PostMapping("getTableField")
    public DataResult getTableField(@RequestBody Map<String, Object> map) throws Exception {

        String databaseName = String.valueOf(map.getOrDefault("databaseName", ""));
        String tableName = String.valueOf(map.getOrDefault("tableName", ""));
        JdbcTemplate jdbcTemplate = getJdbcTemplate();


        String query = "SELECT COLUMN_NAME, COLUMN_TYPE, COLUMN_DEFAULT, IS_NULLABLE, COLUMN_COMMENT " +
                "FROM INFORMATION_SCHEMA.COLUMNS " +
                "WHERE TABLE_SCHEMA = 'qsrd_main_dev' AND TABLE_NAME = 'alarm_information'";

        List<TableColumn> columns = jdbcTemplate.query(query, (rs, rowNum) -> {
            String columnName = rs.getString("COLUMN_NAME");
            String columnType = rs.getString("COLUMN_TYPE");
            String columnDefault = rs.getString("COLUMN_DEFAULT");
            String isNullable = rs.getString("IS_NULLABLE");
            String columnComment = rs.getString("COLUMN_COMMENT");
            return new TableColumn(columnName, columnType, columnDefault, isNullable, columnComment);
        });


        return DataResult.success(columns);
    }


    //获得表的详细信息
    private JdbcTemplate getJdbcTemplate() {
        DataSourceBuilder<?> builder = DataSourceBuilder.create();
        builder.driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306?useUnicode=true&characterEncoding=UTF8&useTimezone=true&serverTimezone=GMT%2B8&allowMultiQueries=true&rewriteBatchedStatements=true")
                .username("root")
                .password("root");
        DataSource dataSource = builder.build();

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }
}
