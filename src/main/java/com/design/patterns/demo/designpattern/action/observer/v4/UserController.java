package com.design.patterns.demo.designpattern.action.observer.v4;

import com.design.patterns.demo.designpattern.action.observer.v2.RegObserver;
import com.design.patterns.demo.designprinciples.isp.apiconllection.UserService;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * UserController类
 *
 * EventBus 翻译为“事件总线”，它提供了实现观察者模式的骨架代码。
 * 我们可以基于此框架，非常容易地在自己的业务场景中实现观察者模式，
 * 不需要从零开始开发。其中，Google Guava EventBus 就是一个比
 * 较著名的 EventBus 框架，它不仅仅支持异步非阻塞模式，同时也支
 * 持同步阻塞模式。
 *
 *
 * @author wangjixue
 * @date 2022/9/3 10:34 AM
 */
public class UserController {

    private static final int DEFAULT_EVENTBUS_THREAD_POOL_SIZE = 20;
    //依赖注入
    private UserService userService;
    private EventBus eventBus;

    public UserController() {
        //同步阻塞模式
        //this.eventBus = new EventBus();
        //异步非阻塞模式
        this.eventBus = new AsyncEventBus(Executors.newFixedThreadPool(DEFAULT_EVENTBUS_THREAD_POOL_SIZE));
    }

    public void setRegObservers(List<RegObserver> regObservers) {
        for (RegObserver regObserver : regObservers) {
            //注册观察者
            eventBus.register(regObserver);
        }
    }

    public void removeRegObservers(RegObserver regObserver){
        //注销观察者
        eventBus.unregister(regObserver);
    }

    public Long register(String telephone, String password){
        Long userId = -1L;
        userService.register(telephone, password);
        //观察者发送消息
        eventBus.post(userId);
        return userId;
    }
}
