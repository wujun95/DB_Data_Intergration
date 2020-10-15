package com.db.springcloud.service;

import com.db.springcloud.entities.CommonResult;
import com.db.springcloud.entities.Results;
import com.db.springcloud.entities.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author wujun
 * @create 2020-10-13 14:58
 */

public interface UserService {

    public User findByUsername(@Param("username") String username);
    public User findById(@Param("id") String id);
    public Results login(User user);

}
