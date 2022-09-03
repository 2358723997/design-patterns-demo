package com.design.patterns.demo.designpattern.action.observer.v3;

import com.design.patterns.demo.designpattern.action.observer.v2.PromotionService;
import com.design.patterns.demo.designpattern.action.observer.v2.RegObserver;

/**
 * RegPromotionObserver类
 *
 * 异步非阻塞观察者模式
 *
 * 实现一：在每个 handleRegSuccess() 函数中创建一个新的线程执行代码逻辑；
 *
 * @author wangjixue
 * @date 2022/9/3 10:20 AM
 */
public class RegPromotionObserver implements RegObserver {
    //依赖注入
    private PromotionService service;

    @Override
    public void handleRegSuccess(long userId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                service.issueNewUserExperienceCash(userId);
            }
        }).start();

    }
}
