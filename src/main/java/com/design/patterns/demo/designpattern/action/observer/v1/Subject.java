package com.design.patterns.demo.designpattern.action.observer.v1;

import org.apache.logging.log4j.message.Message;

/**
 * Subject类
 *
 * @author wangjixue
 * @date 8/10/22 10:42 PM
 */
public interface Subject {

    public void registerObserver(Observer observer);

    public void removeObserver(Observer observer);

    public void notifyObservers(Message message);

}
