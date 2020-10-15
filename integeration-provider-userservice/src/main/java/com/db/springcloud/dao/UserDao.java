package com.db.springcloud.dao;

import com.db.springcloud.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
    public User findByUsername(@Param("username") String username);
    public User findById(@Param("id") String id);

}
