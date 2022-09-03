package com.design.patterns.demo.designpattern.action.observer.eventbus;

import com.google.common.base.Preconditions;

import java.lang.reflect.Method;

/**
 * ObserverAction类
 * <p>
 * ObserverAction 类用来表示 @Subscribe 注解的方法，
 * 其中，target 表示观察者类，method 表示方法。
 * 它主要用在 ObserverRegistry 观察者注册表中。
 *
 * @author wangjixue
 * @date 2022/9/3 11:03 AM
 */
public class ObserverAction {

    private Object target;

    private Method method;

    public ObserverAction(Object target, Method method) {
        this.target = Preconditions.checkNotNull(target);
        this.method = method;
        this.method.setAccessible(true);
    }

    public void excute(Object event) {
        try {
            //event是method的方法参数
            method.invoke(target, event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
