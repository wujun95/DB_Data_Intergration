package com.db.springcloud.service;

import com.db.springcloud.entities.DbDataSource;
import com.db.springcloud.entities.test;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wujun
 * @create 2020-10-10 21:44
 */
public interface DbDataSourceService {
    /*
    * mysql
    * */
    public List<DbDataSource> findAllDataSource();
    public DbDataSource findDataSourceByName(@Param("DataSourceName") String DataSourceName);
    public List showMysqlDataBases();

    /*
    * oracle
    * */
    public List<test> findTest();
    public List showOracleDataBases();
}
