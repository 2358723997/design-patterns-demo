package com.design.patterns.demo.designpattern.action.observer.v1;

import org.apache.logging.log4j.message.Message;

/**
 * Demo类
 *
 * 创建型设计模式主要解决“对象的创建”问题，创建型模式是将创建和使用代码解耦
 *
 * 结构型设计模式主要解决“类或对象的组合或组装”问题，结构型模式是将不同功能代码解耦
 *
 * 行为型设计模式主要解决的就是“类或对象之间的交互”问题，行为型模式是将不同的行为代码解耦。
 *
 *
 * 设计模式要干的事情就是解耦。
 *
 * 创建型模式是将创建和使用代码解耦，结构型模式是将不同功能代码解耦，行为型模式是将不同的行为代码解耦，
 *
 *
 * 具体到观察者模式，它是将观察者和被观察者代码解耦。
 *
 * 借助设计模式，我们利用更好的代码结构，将一大坨代码拆分成职责更单一的小类，
 * 让其满足开闭原则、高内聚松耦合等特性，以此来控制和应对代码的复杂性，提高代码的可扩展性。
 *
 * 观察者模式（Observer Design Pattern）也被称为发布订阅模式（Publish-Subscribe Design Pattern）。
 * 在对象之间定义一个一对多的依赖，当一个对象状态改变的时候，所有依赖的对象都会自动收到通知。
 *
 * 被依赖的对象叫作被观察者（Observable），依赖的对象叫作观察者（Observer）
 *
 * 观察者模式的应用场景非常广泛，小到代码层面的解耦，大到架构层面的系统解耦，再或者一些产品的设计思路，
 * 都有这种模式的影子，比如，邮件订阅、RSS Feeds，本质上都是观察者模式。不同的应用场景和需求下，这个
 * 模式也有截然不同的实现方式，
 * 有同步阻塞的实现方式，也有异步非阻塞的实现方式；有进程内的实现方式，也有跨进程的实现方式。
 *
 * v1是观察者模式的“模板代码”：
 *
 * @author wangjixue
 * @date 8/10/22 10:47 PM
 */
public class Demo {
    public static void main(String[] args) {
        Subject subject = new ConcreteSubject();

        subject.registerObserver(new ConcreteObserverOne());
        subject.registerObserver(new ConcreteObserverTwo());

        subject.notifyObservers(new Message() {
            @Override
            public String getFormattedMessage() {
                return null;
            }

            @Override
            public String getFormat() {
                return null;
            }

            @Override
            public Object[] getParameters() {
                return new Object[0];
            }

            @Override
            public Throwable getThrowable() {
                return null;
            }
        });
    }
}
