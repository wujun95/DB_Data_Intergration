package com.db.springcloud.dao;

import com.db.springcloud.entities.DbDataSource;
import com.db.springcloud.entities.test;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author wujun
 * @create 2020-10-14 18:56
 */
@Mapper
public interface DataSourceDao {

    public List<DbDataSource> findAllDataSource();
    public DbDataSource findDataSourceByname(@Param("DataSourceName") String DataSourceName);
    public List showMysqlDataBases();

    public List findTest();
    public List showOracleDataBases();
}
