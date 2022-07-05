package com.design.patterns.demo.designpattern.structure.proxy.staticproxy.v1;

import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.MetricsCollector;
import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.RequestInfo;
import com.design.patterns.demo.coderestructure.programmingspecification.name.User;

/**
 * UserControllerProxy类
 *
 * 为了将框架代码和业务代码解耦，代理模式就派上用场了
 *
 * 第一种 实现相同接口的静态代理模式：
 *
 * 代理类UserControllerProxy和原始类UserController实现相同的接口IUserController。
 * UserController类只负责业务功能。代理类UserControllerProxy负责在业务代码执行前后
 * 附加其他逻辑代码，并通过委托的方式调用原始类来执行业务代码。
 * @author wangjixue
 * @date 7/5/22 11:40 PM
 */
public class UserControllerProxy implements IUserController{
    //依赖注入
    private UserController userController;

    private MetricsCollector metricsCollector;

    /**
     * 用户注册
     *
     * @param user
     */
    @Override
    public void register(User user){
        long startTimestamp = System.currentTimeMillis();

        //委托
        userController.register(user);

        long responseTime = System.currentTimeMillis() - startTimestamp;
        RequestInfo requestInfo = new RequestInfo("register",responseTime,startTimestamp);
        metricsCollector.recordRequest(requestInfo);
    }

    /**
     * 用户登录
     *
     * @param telephone
     * @param password
     */
    @Override
    public void login(String telephone, String password){
        long startTimestamp = System.currentTimeMillis();

        //委托
        userController.login(telephone,password);

        long responseTime = System.currentTimeMillis() - startTimestamp;
        RequestInfo requestInfo = new RequestInfo("login",responseTime,startTimestamp);
        metricsCollector.recordRequest(requestInfo);
    }
}
