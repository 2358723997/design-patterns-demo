package com.design.patterns.demo.designprinciples.dip.di.counterexample;

import com.design.patterns.demo.designprinciples.dip.di.MessageSender;

/**
 * Notification类
 *
 * @author wangjixue
 * @date 1/2/22 6:35 PM
 */
public class Notification {

    private MessageSender messageSender;

    // 非依赖注入实现方式
    public Notification() {
        this.messageSender = new MessageSender();
    }

    public void sendMessage(String cellphone, String message){
        this.messageSender.send(cellphone,message);
    }
}
