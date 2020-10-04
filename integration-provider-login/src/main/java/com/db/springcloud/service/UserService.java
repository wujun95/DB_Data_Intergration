package com.db.springcloud.service;

import com.db.springcloud.entities.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserService {
    public User findByUsername(@Param("username") String username);
    public int addUser(User user);
    public User findById(@Param("uid") String uid);
//    public int edit(User user);
//    public int editPassword(User user);
//    public int delete(String ids);
//    public List<User> findList(Map<String, Object> queryMap);
//    public int getTotal(Map<String, Object> queryMap);
}
