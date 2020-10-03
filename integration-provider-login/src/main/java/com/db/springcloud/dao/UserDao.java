package com.db.springcloud.dao;

import com.db.springcloud.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {
    public User findByUsername(@Param("username") String username);
    public int addUser(User user);
//    public int edit(User user);
//    public int editPassword(User user);
//    public int delete(String ids);
//    public List<User> findList(Map<String, Object> queryMap);
//    public int getTotal(Map<String, Object> queryMap);
}
