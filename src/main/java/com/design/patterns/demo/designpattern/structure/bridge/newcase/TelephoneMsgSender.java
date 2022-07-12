package com.design.patterns.demo.designpattern.structure.bridge.newcase;

import java.util.List;

/**
 * TelephoneMsgSender类
 *
 * @author wangjixue
 * @date 7/13/22 12:02 AM
 */
public class TelephoneMsgSender implements MsgSender{
    private List<String> telephones;

    public TelephoneMsgSender(List<String> telephones) {
        this.telephones = telephones;
    }

    public void setTelephones(List<String> telephones) {
        this.telephones = telephones;
    }

    @Override
    public void send(String message) {
        //TODO 自动语音电话
    }
}
