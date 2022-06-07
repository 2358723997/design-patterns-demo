package com.design.patterns.demo.coderestructure.demo.performancecounters.v1;

import java.util.concurrent.TimeUnit;

import com.design.patterns.demo.coderestructure.programmingspecification.name.User;

/**
 * UserController类
 *
 * @author wangjixue
 * @date 6/7/22 11:07 PM
 */
public class UserController {
    private Metrics metrics = new Metrics();

    public UserController() {
        metrics.startRepeatedReport(60, TimeUnit.SECONDS);
    }

    /**
     * 用户注册
     *
     * @param user
     */
    public void register(User user){
        long startTimestamp = System.currentTimeMillis();
        metrics.recordTimestamp("register",startTimestamp);
        //业务逻辑
        long responseTime = System.currentTimeMillis() - startTimestamp;
        metrics.recordResponseTime("register",responseTime);
    }

    /**
     * 用户登录
     *
     * @param telephone
     * @param password
     */
    public void login(String telephone, String password){
        long startTimestamp = System.currentTimeMillis();
        metrics.recordTimestamp("login",startTimestamp);
        //业务逻辑
        long responseTime = System.currentTimeMillis() - startTimestamp;
        metrics.recordResponseTime("login",responseTime);
    }
}
