package com.db.springcloud.service;

import com.db.springcloud.entities.DbDataSource;
import com.db.springcloud.entities.Results;
import com.db.springcloud.entities.test;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author wujun
 * @create 2020-10-14 19:26
 */
public interface DataSourceService {
    public Results findAllDataSource();
    public DbDataSource findDataSourceByname(@Param("DataSourceName")String dataSourceName);
    public Results showMysqlDataBases();
    public Results findDatabases(String dataSourceName);

    public Results findTest();
    public Results showOracleDataBases();
}
