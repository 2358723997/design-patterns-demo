package com.design.patterns.demo.designpattern.action.observer.v1;

import org.apache.logging.log4j.message.Message;

/**
 * ConcreteObserverOne类
 *
 * @author wangjixue
 * @date 8/10/22 10:46 PM
 */
public class ConcreteObserverTwo implements Observer {
    @Override
    public void update(Message message) {
        //TODO 获取消息通知， 执行逻辑
        System.out.println("ConcreteObserverTwo is notified.");
    }
}
