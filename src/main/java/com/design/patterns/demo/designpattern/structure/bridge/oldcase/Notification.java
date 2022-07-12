package com.design.patterns.demo.designpattern.structure.bridge.oldcase;

import java.util.List;

/**
 * Notification类
 *
 * 我们讲过一个 API 接口监控告警的例子：根据不同的告警规则，触发不同类型的告警。
 * 告警支持多种通知渠道，包括：邮件、短信、微信、自动语音电话。通知的紧急程度有多
 * 种类型，包括：SEVERE（严重）、URGENCY（紧急）、NORMAL（普通）、TRIVIAL（
 * 无关紧要）。不同的紧急程度对应不同的通知渠道。比如，SERVE（严重）级别的消息会
 * 通过“自动语音电话”告知相关人员。
 *
 * 在当时的代码实现中，关于发送告警信息那部分代码，我们只给出了粗略的设计，现在我们
 * 来一块实现一下。我们先来看最简单、最直接的一种实现方式。
 *
 * @author wangjixue
 * @date 7/12/22 11:45 PM
 */
public class Notification {
    private List<String> emailAddress;
    private List<String> telephones;
    private List<String> wechatIds;

    public Notification() {
    }

    public void setEmailAddress(List<String> emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setTelephones(List<String> telephones) {
        this.telephones = telephones;
    }

    public void setWechatIds(List<String> wechatIds) {
        this.wechatIds = wechatIds;
    }

    public void notify(NotificationEmergencyLevel level, String message){
        if(level.equals(NotificationEmergencyLevel.SEVERE)){
            //TODO 自动语音电话
        }else if(level.equals(NotificationEmergencyLevel.URGENCY)){
            //TODO 发微信
        }else if(level.equals(NotificationEmergencyLevel.NORMAL)){
            //TODO 发邮件
        }else if(level.equals(NotificationEmergencyLevel.TRIVIAL)){
            //TODO 发邮件
        }
    }
}