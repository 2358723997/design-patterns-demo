package com.design.patterns.demo.designpattern.action.observer.eventbus;

import com.google.common.util.concurrent.MoreExecutors;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * EventBus类
 *
 * EventBus中两个核心函数 register() 和 post() 的实现原理。
 * 弄懂了它们，基本上就弄懂了整个 EventBus 框架。
 *
 * https://static001.geekbang.org/resource/image/ce/c6/ce842666fa3dc92bb8f4f2d8e75d12c6.jpg?wh=2563*1333
 *
 * https://static001.geekbang.org/resource/image/bf/45/bf7ef52a40b1e35b18f369265caca645.jpg?wh=2528*953
 *
 * 最关键的一个数据结构是 Observer 注册表，记录了消息类型和可接收消息函数的对应关系。当调用 register() 函数注册观察者的时候，
 * EventBus 通过解析 @Subscribe 注解，生成 Observer 注册表。
 *
 * 当调用 post() 函数发送消息的时候，EventBus 通过注册表找到相应的可接收消息的函数，然后通过 Java 的反射语法来动态地创建对象、
 * 执行函数。
 *
 * 对于同步阻塞模式，EventBus 在一个线程内依次执行相应的函数。
 *
 * 对于异步非阻塞模式，EventBus 通过一个线程池来执行相应的函数。
 *
 * 弄懂了原理，实现起来就简单多了。整个小框架的代码实现包括 5 个类：
 * EventBus、AsyncEventBus、Subscribe、ObserverAction、ObserverRegistry。
 *
 * @author wangjixue
 * @date 2022/9/3 10:53 AM
 */
public class EventBus {

    private Executor executor;

    private ObserverRegistry registry = new ObserverRegistry();

    public EventBus() {
        //MoreExecutors.directExecutor() 是 Google Guava 提供的工具类，
        // 看似是多线程，实际上是单线程。之所以要这么实现，主要还是为了跟
        // AsyncEventBus 统一代码逻辑，做到代码复用。
        executor =  MoreExecutors.directExecutor();
    }

    public EventBus(Executor executor) {
        this.executor = executor;
    }

    public void register(Object object){
        registry.register(object);
    }

    public void post(Object event){
        List<ObserverAction> observerActions =  registry.getMatchedObserverActions(event);
        for (ObserverAction observerAction : observerActions) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    observerAction.excute(event);
                }
            });
        }
    }
}
