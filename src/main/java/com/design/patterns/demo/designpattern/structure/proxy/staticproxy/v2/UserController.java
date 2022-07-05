package com.design.patterns.demo.designpattern.structure.proxy.staticproxy.v2;


import com.design.patterns.demo.coderestructure.programmingspecification.name.User;
import com.design.patterns.demo.designpattern.structure.proxy.staticproxy.v1.IUserController;

/**
 * UserController类
 * 被代理类
 * @author wangjixue
 * @date 7/5/22 11:27 PM
 */
public class UserController{

    /**
     * 用户注册
     *
     * @param user
     */
    public void register(User user){
        //TODO 业务逻辑
    }

    /**
     * 用户登录
     *
     * @param telephone
     * @param password
     */
    public void login(String telephone, String password){
        //TODO 业务逻辑
    }
}
