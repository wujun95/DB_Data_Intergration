package com.db.springcloud.service.impl;

import com.db.springcloud.commonutils.JwtUtils;
import com.db.springcloud.dao.UserDao;
import com.db.springcloud.entities.Results;
import com.db.springcloud.entities.User;
import com.db.springcloud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author wujun
 * @create 2020-10-13 15:02
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public Results login(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            log.info("用户名或密码为空，登录失败");
            return Results.error().data("error","用户名或密码为空，登录失败");
        }
        User userInfo = userDao.findByUsername(username);
        if (userInfo == null){
            log.info("未找到用户,登录失败");
            return Results.error().data("error","未找到用户，登录失败");
        }
        if (!userInfo.getUsername().equals(username)||!userInfo.getPassword().equals(password)){
            log.info("用户名或密码错误，登录失败");
            return Results.error().data("error","用户名或密码错误，登录失败");
        }
        //登录成功，生成token字符串
        String loginToken = JwtUtils.getJwtToken(userInfo.getId(), userInfo.getUsername());
        log.info("用户登录成功");
        return Results.success().data("loginToken",loginToken);
    }

}
