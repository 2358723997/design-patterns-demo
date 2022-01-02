package com.design.patterns.demo.designprinciples.dip.di.positiveexample;

import com.design.patterns.demo.designprinciples.dip.di.MessageSender;

/**
 * Notification类
 *
 * @author wangjixue
 * @date 1/2/22 6:35 PM
 */
public class Notification {

    private MessageSender messageSender;

    //依赖注入实现方式,注入对象可以是抽象类也可以是接口
    //通过依赖注入的方式来将依赖的类对象传递进来，这样就提高了代码的扩展性，我们可以灵活地替换依赖的类,此处使用
    // 接口+组合方式替代继承可以显著提高代码的扩展性
    public Notification(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void sendMessage(String cellphone, String message) {
        this.messageSender.send(cellphone, message);
    }
}
