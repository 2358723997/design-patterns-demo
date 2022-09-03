package com.design.patterns.demo.designpattern.action.observer.eventbus;

import java.util.concurrent.Executor;

/**
 * AsyncEventBus类
 *
 * @author wangjixue
 * @date 2022/9/3 11:05 AM
 */
public class AsyncEventBus extends EventBus{

    //为了实现异步非阻塞的观察者模式，它就不能再继续使用 MoreExecutors.directExecutor() 了，
    // 而是需要在构造函数中，由调用者注入线程池。
    public AsyncEventBus(Executor executor) {
        super(executor);
    }
}
