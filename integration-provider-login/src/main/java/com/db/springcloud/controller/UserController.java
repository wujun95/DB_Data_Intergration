package com.db.springcloud.controller;

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
    public CommonResult userLogin(@RequestBody User user,String cpacha, HttpServletRequest request){
        User userInfo = userService.findByUsername(user.getUsername());
        System.out.println(user.getUsername());
        Object loginCpacha = request.getSession().getAttribute("loginCpacha");

        if (userInfo!=null){
            if (!StringUtils.isEmpty(user.getPassword())){
                if (!StringUtils.isEmpty(cpacha)){
                    if(user.getPassword().equals(userInfo.getPassword())){
                        if (cpacha.toUpperCase().equals(loginCpacha.toString().toUpperCase())){
                            return new CommonResult(200,"登陆成功，serverPort："+serverPort,userInfo.getUsername()+"--"+cpacha);
                        }else {
                            return new CommonResult(401,"登录失败，验证码错误，serverPort："+serverPort,null);
                        }
                    }else{
                        return new CommonResult(401,"登录失败，密码或用户名错误错误，serverPort："+serverPort,null);
                    }
                }else{
                    return new CommonResult(401,"登陆失败，验证码为空请填写验证码，serverPort："+serverPort,null);
                }
            }else {
                return new CommonResult(401,"登录失败，密码为空请填写正确密码，serverPort："+serverPort,null);
            }
        }else {
            return new CommonResult(401,"登陆失败，该账户不存在，serverPort："+serverPort, null);
        }
    }
}
