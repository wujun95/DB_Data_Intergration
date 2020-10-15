package com.db.springcloud.dao;

import com.db.springcloud.entities.DbDataSource;
import com.db.springcloud.entities.test;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wujun
 * @create 2020-10-10 21:20
 */
@Mapper
public interface DbDataSourceDao {

    public List<DbDataSource> findAllDataSource();
    public DbDataSource findDataSourceByname(@Param("DataSourceName")String DataSourceName);
    public List showMysqlDataBases();

    public List<test> findTest();
    public List showOracleDataBases();
}
