package com.db.springcloud.entities;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wujun
 * @create 2020-10-12 21:18
 */

@Data
public class Results {

    private Integer code; //返回码
    private String message; //返回消息
    private Map<String ,Object> data = new HashMap<String ,Object>();
    private Results(){
    }

    public static Results success(){
        Results successResults = new Results();
        successResults.setCode(200);
        successResults.setMessage("请求成功");
        return  successResults;
    }

    public static Results error(){
        Results errorResults = new Results();
        errorResults.setCode(500);
        errorResults.setMessage("请求失败");
        return  errorResults;
    }

    public Results code (Integer code){
        this.code  = code;
        return this;
    }

    public Results message(String message){
        this.message = message;
        return this;
    }

    public Results data(Map<String ,Object> map){
        this.data = map;
        return this;
    }

    public Results data(String key,Object value){
        this.data.put(key,value);
        return  this;
    }

}
