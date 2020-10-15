package com.db.springcloud.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.db.springcloud.dao.DataSourceDao;
import com.db.springcloud.entities.DbDataSource;
import com.db.springcloud.entities.Results;
import com.db.springcloud.entities.test;
import com.db.springcloud.service.DataSourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author wujun
 * @create 2020-10-14 19:30
 */

@Service
@Slf4j
public class DataSourceServiceImpl implements DataSourceService {

    @Resource
    private DataSourceDao dataSourceDao;

    @Value("${server.port}")
    private String serverPort;

    @Override
    public Results findAllDataSource() {
        List<DbDataSource> allDataSource = dataSourceDao.findAllDataSource();
        if (allDataSource.isEmpty()){
            log.info(serverPort+"未查询到所有数据源，查询失败");
            return Results.error().data("error",serverPort+"未查询到所有数据源，查询失败");
        }
        log.info(serverPort+"查询所有所有数据源成功");
        return Results.success().data("DataSource",allDataSource);
    }

    @Override
    public DbDataSource findDataSourceByname(String dataSourceName) {
        return dataSourceDao.findDataSourceByname(dataSourceName);
    }

    @Override
    public Results showMysqlDataBases() {
        List mysqlDataBases = dataSourceDao.showMysqlDataBases();
        if (mysqlDataBases.isEmpty()){
            log.info(serverPort+"获取mysql所有数据库失败");
            return Results.error().data("error",serverPort+"获取mysql所有数据库失败");
        }
        log.info(serverPort+"获取mysql所有数据库成功");
        return Results.success().data("mysqlDataBases",mysqlDataBases);
    }

    @Override
    public Results findDatabases(String DataSourceName) {
        DbDataSource dataSource = dataSourceDao.findDataSourceByname(DataSourceName);
        if (StringUtils.isEmpty(dataSource.getName())){
            log.info(serverPort+"查找数据源失败");
            return Results.error().data("error",serverPort+"查找数据源失败");
        }
        if (dataSource.getName().equals(DataSourceName)&&DataSourceName.equals("mysql") ){
            log.info(serverPort+"获取mysql所有数据库成功");
            Results mysqlDataBases = showMysqlDataBases();
            return mysqlDataBases;
        }
        log.info(serverPort+"获取oracle所有数据库成功");
        Results oracleDataBases = showOracleDataBases();
        return oracleDataBases;
    }

    /*
    *Oracle
    * */
    @Override
    @DS("slave_1")
    public Results findTest() {
        List test = dataSourceDao.findTest();
        if (test.isEmpty()){
            log.info(serverPort+"获取测试数据失败");
            return Results.error().data("error",serverPort+"获取测试数据失败");
        }
        log.info(serverPort+"获取测试数据成功");
        return Results.success().data("test",test);
    }

    @Override
    @DS("slave_1")
    public Results showOracleDataBases() {
        List oracleDataBases = dataSourceDao.showOracleDataBases();
        if (oracleDataBases.isEmpty()){
            log.info(serverPort+"获取oracle所有数据库失败");
            return Results.error().data("error","获取oracle所有数据库失败");
        }
        log.info(serverPort+"获取所有数据库成功");
        return Results.success().data("oracleDataBase",oracleDataBases);
    }
}
