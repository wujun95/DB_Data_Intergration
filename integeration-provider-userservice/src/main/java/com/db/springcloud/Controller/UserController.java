package com.db.springcloud.Controller;

import com.db.springcloud.commonutils.JwtUtils;
import com.db.springcloud.entities.Results;
import com.db.springcloud.entities.User;
import com.db.springcloud.service.UserService;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author wujun
 * @create 2020-10-13 16:05
 */
@RestController
@RequestMapping("/system/user")
@CrossOrigin
public class UserController {

    @Resource
    private UserService userService;

    /*用户登录接口*/
    @PostMapping("/login")
    public Results userLogin(@RequestBody User user){
        Results loginToken = userService.login(user);
        return loginToken;
    }

    /*获取用户信息*/
    @GetMapping("/getUserInfo")
    public Results userInfo(HttpServletRequest request){
        String memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(request);
        User userInfo = userService.findById(memberIdByJwtToken);
        return Results.success().data("userInfo",userInfo);
    }
}
