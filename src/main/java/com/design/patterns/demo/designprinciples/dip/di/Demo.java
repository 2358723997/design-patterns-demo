package com.design.patterns.demo.designprinciples.dip.di;

import com.design.patterns.demo.designprinciples.dip.di.positiveexample.Notification;

/**
 * Demo类
 *
 * @author wangjixue
 * @date 1/2/22 6:39 PM
 */
public class Demo {
    public static void main(String[] args) {
        //Notification notification = new Notification();
        //notification.sendMessage("cellphone","非依赖注入实现方式");

        MessageSender messageSender = new MessageSender();
        Notification notification = new Notification(messageSender);

        notification.sendMessage("cellphone","依赖注入实现方式");
    }
}
