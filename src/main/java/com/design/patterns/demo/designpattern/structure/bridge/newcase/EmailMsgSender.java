package com.design.patterns.demo.designpattern.structure.bridge.newcase;

import java.util.List;

/**
 * TelephoneMsgSender类
 *
 * @author wangjixue
 * @date 7/13/22 12:02 AM
 */
public class EmailMsgSender implements MsgSender{
    private List<String> emails;

    public EmailMsgSender(List<String> emails) {
        this.emails = emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    @Override
    public void send(String message) {
        //TODO 发送邮件
    }
}
