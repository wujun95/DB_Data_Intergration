package com.db.springcloud.service.impl;

import com.db.springcloud.dao.UserDao;
import com.db.springcloud.entities.User;
import com.db.springcloud.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;


    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public User findById(String uid) {
        return userDao.findById(uid);
    }
}
