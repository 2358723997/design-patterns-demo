package com.design.patterns.demo.designpattern.action.observer.v1;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.message.Message;

/**
 * ConcreteSubject类
 *
 * @author wangjixue
 * @date 8/10/22 10:43 PM
 */
public class ConcreteSubject implements Subject{

    private List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Message message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
