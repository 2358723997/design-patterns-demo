package com.design.patterns.demo.designpattern.structure.proxy;


import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.MetricsCollector;
import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.RequestInfo;
import com.design.patterns.demo.coderestructure.programmingspecification.name.User;

/**
 * UserController类
 *
 * 性能计数器存在的问题：
 * 第一，性能计数器框架代码侵入到业务代码中，跟业务代码高度耦合。如果未来需要替换这个框架，那替换的成本会比较大。
 * 第二，收集接口请求的代码跟业务代码无关，本就不应该放到一个类中。业务类最好职责更加单一，只聚焦业务处理。
 *
 * @author wangjixue
 * @date 7/5/22 11:27 PM
 */
public class UserController {

    //依赖注入
    private MetricsCollector metricsCollector;

    /**
     * 用户注册
     *
     * @param user
     */
    public void register(User user){
        long startTimestamp = System.currentTimeMillis();
        //TODO 业务逻辑
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
    public void login(String telephone, String password){
        long startTimestamp = System.currentTimeMillis();
        //TODO 业务逻辑
        long responseTime = System.currentTimeMillis() - startTimestamp;
        RequestInfo requestInfo = new RequestInfo("login",responseTime,startTimestamp);
        metricsCollector.recordRequest(requestInfo);
    }
}
