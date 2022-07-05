package com.design.patterns.demo.designpattern.structure.proxy.staticproxy.v2;

import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.MetricsCollector;
import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.RequestInfo;
import com.design.patterns.demo.coderestructure.programmingspecification.name.User;

/**
 * UserControllerProxy类
 *
 * 第二种 继承被代理对象的静态代理模式：
 *
 * 适用场景：如果原始类并没有定义接口，并且原始类代码并不是我们开发维护的
 * （比如它来自一个第三方的类库），我们也没办法直接修改原始类，这时候我们一般都是采用继承的方式。
 * 这里也不例外。我们让代理类继承原始类，然后扩展附加功能。
 *
 * @author wangjixue
 * @date 7/5/22 11:46 PM
 */
public class UserControllerProxy extends UserController{

    private MetricsCollector metricsCollector;
    /**
     * 用户注册
     *
     * @param user
     */
    @Override
    public void register(User user){
        long startTimestamp = System.currentTimeMillis();

        //调用父类方法
        super.register(user);

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

        //调用父类方法
        super.login(telephone,password);

        long responseTime = System.currentTimeMillis() - startTimestamp;
        RequestInfo requestInfo = new RequestInfo("login",responseTime,startTimestamp);
        metricsCollector.recordRequest(requestInfo);
    }
}
