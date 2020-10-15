package com.db.springcloud.entities;

import lombok.Data;

/**
 * @author wujun
 * @create 2020-10-13 16:43
 */
@Data
public class UserRegister {

    private String username; //用户名
    private String phone; //手机号
    private String password; //密码
    private String cpacha;  //验证码
}
