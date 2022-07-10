package com.design.patterns.demo.designpattern.structure.proxy.dynamicProxy;

import com.design.patterns.demo.designpattern.structure.proxy.UserController;
import com.design.patterns.demo.designpattern.structure.proxy.staticproxy.v1.IUserController;

/**
 * DynamicProxyHandler类
 *
 * @author wangjixue
 * @date 7/10/22 10:26 PM
 */
public class DynamicProxyDemoTest {
    public static void main(String[] args) {
        MetricsCollectorProxy proxy = new MetricsCollectorProxy();
        IUserController userController = (IUserController) proxy.createProxy(new UserController());
    }
}
