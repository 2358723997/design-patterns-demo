package com.design.patterns.demo.designpattern.structure.proxy.staticproxy.v1;


import com.design.patterns.demo.coderestructure.programmingspecification.name.User;

/**
 * UserController类
 * 被代理类
 * @author wangjixue
 * @date 7/5/22 11:27 PM
 */
public class UserController implements IUserController{

    /**
     * 用户注册
     *
     * @param user
     */
    @Override
    public void register(User user){
        //TODO 业务逻辑
    }

    /**
     * 用户登录
     *
     * @param telephone
     * @param password
     */
    @Override
    public void login(String telephone, String password){
        //TODO 业务逻辑
    }
}
