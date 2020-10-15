package com.db.springcloud.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.db.springcloud.dao.DbDataSourceDao;
import com.db.springcloud.entities.DbDataSource;
import com.db.springcloud.entities.test;
import com.db.springcloud.service.DbDataSourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wujun
 * @create 2020-10-10 21:45
 */

@Service
public class DbDataSourceServiceImpl implements DbDataSourceService {

    @Resource
    private DbDataSourceDao dbDataSourceDao;

    @Override
    public List<DbDataSource> findAllDataSource() {
        return dbDataSourceDao.findAllDataSource();
    }

    @Override
    public DbDataSource findDataSourceByName(String DataSourceName) {
        return dbDataSourceDao.findDataSourceByname(DataSourceName);
    }

    @Override
    public List showMysqlDataBases() {
        return dbDataSourceDao.showMysqlDataBases();
    }

    /*
    * oracle
    * */
    @Override
    @DS("slave_1")
    public List<test> findTest() {
        return dbDataSourceDao.findTest();
    }

    @Override
    @DS("slave_1")
    public List showOracleDataBases() {
        return dbDataSourceDao.showOracleDataBases();
    }
}
