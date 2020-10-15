package com.db.springcloud.controller;

import com.db.springcloud.entities.Results;
import com.db.springcloud.service.DataSourceService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author wujun
 * @create 2020-10-14 20:48
 */
@RestController
@CrossOrigin
@RequestMapping("/system/datadisplay")
public class DataDisplayController {

    @Resource
    private DataSourceService dataSourceService;

    @GetMapping(value = "/datasources")
    public Results findAllDataSources(){
        Results allDataSource = dataSourceService.findAllDataSource();
        return allDataSource;
    }

    @GetMapping(value = "/databases")
    public Results findAllDataBases(@RequestParam(value = "DataSourceName",required = false) String DataSourceName){
        Results databases = dataSourceService.findDatabases(DataSourceName);
        return databases;
    }

    @GetMapping(value = "/findtest")
    public Results findTest(){
        Results test = dataSourceService.findTest();
        return test;
    }
}
