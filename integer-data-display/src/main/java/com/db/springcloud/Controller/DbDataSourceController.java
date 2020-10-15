package com.db.springcloud.Controller;

import com.db.springcloud.entities.CommonResult;
import com.db.springcloud.entities.DbDataSource;
import com.db.springcloud.entities.test;
import com.db.springcloud.service.DbDataSourceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wujun
 * @create 2020-10-10 21:50
 */
@RequestMapping("/display")
@RestController
public class DbDataSourceController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DbDataSourceService sourceService;

    @GetMapping(value = "/DataSourceName")
    public CommonResult  DataSourceByName(@RequestParam(value = "DataSourceName" ,required = false) String DataSourceName){
        DbDataSource dataSourceByName = sourceService.findDataSourceByName(DataSourceName);
        if (dataSourceByName == null){
            return new CommonResult(500,"查询数据源失败"+serverPort,null);
        }
        return new CommonResult(200,"获取数据源成功"+serverPort,dataSourceByName);
    }

    //获取所有数据源
    @GetMapping(value = "/datasource")
    public CommonResult<DbDataSource> findAllDataSource(){
        List<DbDataSource> allDataSource = sourceService.findAllDataSource();
        if (allDataSource == null){
            return new CommonResult(500,"获取全部数据源失败:"+serverPort,null);
        }
        return new CommonResult(200,"获取全部数据源成功:"+serverPort,allDataSource);
    }

    //获取对应数据源下的所有数据库
    @GetMapping(value = "/database")
    public CommonResult findDatabases(@RequestParam(value = "DataSourceName",required = false) String DataSourceName){
        //查找对应的数据源
        DbDataSource dataSourceByName = sourceService.findDataSourceByName(DataSourceName);
        if (StringUtils.isEmpty(dataSourceByName.getName())){
            return new CommonResult(500,"选取选取数据源失败",null);
        }
        if (dataSourceByName.getName().equals(DataSourceName)&&DataSourceName.equals("mysql")){
            List mysqlDataBases = sourceService.showMysqlDataBases();
            return new CommonResult(200,"获取Mysql中数据库成功:"+serverPort,mysqlDataBases);
        }
        List oracleDataBases = sourceService.showOracleDataBases();
        return new CommonResult(200,"获取Oracle中数据库成功："+serverPort,oracleDataBases);
    }

    //获取对应数据库下的所有表
//    @GetMapping(value = "/datatable")
//    public CommonResult findDatatables(@RequestParam(value = "DataBaseName",required = false) String DataBaseName){
//
//    }

    @GetMapping(value = "/test")
    public CommonResult<test> findTest(){
        List<test> test = sourceService.findTest();
        if (test == null){
            return new CommonResult(500,"获取测试数据失败:"+serverPort,null);
        }
        return new CommonResult(200,"获取测试数据成功:",test);
    }
}
