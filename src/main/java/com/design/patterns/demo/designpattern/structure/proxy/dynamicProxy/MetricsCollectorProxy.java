package com.design.patterns.demo.designpattern.structure.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.MetricsCollector;
import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.RequestInfo;

/**
 * MetricsCollectorProxy类
 *
 * 定义：动态代理（Dynamic Proxy），就是我们不事先为每个原始类编写代理类，而是在运行的时候，
 * 动态地创建原始类对应的代理类，然后在系统中用代理类替换掉原始类。
 *
 *
 *
 * @author wangjixue
 * @date 7/10/22 10:12 PM
 */
public class MetricsCollectorProxy {
    private MetricsCollector metricsCollector;

    public MetricsCollectorProxy() {
        this.metricsCollector = new MetricsCollector(null);
    }

    public Object createProxy(Object proxiedObject){
        Class<?>[] interfaces = proxiedObject.getClass().getInterfaces();
        DynamicProxyHandler proxyHandler = new DynamicProxyHandler(proxiedObject);
        return Proxy.newProxyInstance(proxiedObject.getClass().getClassLoader(),interfaces,proxyHandler);
    }

    private class DynamicProxyHandler implements InvocationHandler {
        private Object proxiedObject;

        public DynamicProxyHandler(Object proxiedObject) {
            this.proxiedObject = proxiedObject;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            long startTimestamp = System.currentTimeMillis();
            Object result = method.invoke(proxiedObject, args);
            long endTimeStamp = System.currentTimeMillis();
            long responseTime = endTimeStamp - startTimestamp;
            String apiName = proxiedObject.getClass().getName() + ":" + method.getName();
            RequestInfo requestInfo = new RequestInfo(apiName,responseTime,startTimestamp);
            metricsCollector.recordRequest(requestInfo);
            return result;
        }
    }
}
