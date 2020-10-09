package com.db.springcloud.controller;

import com.db.springcloud.commonutils.JwtUtils;
import com.db.springcloud.entities.CommonResult;
import com.db.springcloud.entities.User;
import com.db.springcloud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@Slf4j
public class UserController {

    @Resource
    UserService userService;

    @Value("${server.port}")
    private String serverPort;

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping(value = "/system/user/addUser")
    public CommonResult addUser(@RequestBody User user){
        int result = userService.addUser(user);
        log.info("***插入结果" + result);
        if (result >0){
            return new CommonResult(200,"插入成功,端口号："+serverPort,result);
        }else {
            return new CommonResult(401,"插入失败，端口号："+serverPort,null);
        }
    }

    /**
     * 用户查找
     * @param username
     * @return
     */
    @GetMapping(value = "/system/user/get/{username}")
    public CommonResult getUserByName(@PathVariable("username") String username){
        User user= userService.findByUsername(username);
        log.info("***查询结果" + user);
        if (user!=null){
            return new CommonResult(200,"查询成功,serverPort："+serverPort,user);
        }else {
            return new CommonResult(404,"没有对应记录，查询用户："+username,null);
        }
    }

    @PostMapping(value = "/system/user/login")
    public CommonResult userLogin(@RequestBody User user){
        User userInfo = userService.findByUsername(user.getUsername());
        System.out.println(user.getUsername());
        if (userInfo!=null){
            if (!StringUtils.isEmpty(user.getPassword())){
                    if(user.getPassword().equals(userInfo.getPassword())){
                        //将用户信息放入token字符串中
                        String token = JwtUtils.getJwtToken(userInfo.getId(),userInfo.getUsername());
                        return new CommonResult(200,"登陆成功，serverPort："+serverPort,token);
                    }else{
                        return new CommonResult(401,"登录失败，密码或用户名错误错误，serverPort："+serverPort,null);
                    }
            }else {
                return new CommonResult(401,"登录失败，密码为空请填写正确密码，serverPort："+serverPort,null);
            }
        }else {
            return new CommonResult(401,"登陆失败，该账户不存在，serverPort："+serverPort, null);
        }
    }

    //根据token获取用户信息
    @GetMapping(value = "/system/user/getUserInfo")
    public CommonResult getUserInfo(HttpServletRequest request){
        //调用jwt工具类的方法。根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        User user = userService.findById(memberId);
        return new CommonResult(200,"获取用户信息成功,serverPort:"+serverPort,user);
    }
}
