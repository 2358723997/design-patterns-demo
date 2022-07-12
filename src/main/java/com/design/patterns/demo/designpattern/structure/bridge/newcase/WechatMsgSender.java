package com.design.patterns.demo.designpattern.structure.bridge.newcase;

import java.util.List;

/**
 * TelephoneMsgSender类
 *
 * @author wangjixue
 * @date 7/13/22 12:02 AM
 */
public class WechatMsgSender implements MsgSender{
    private List<String> wechatIds;

    public WechatMsgSender(List<String> wechatIds) {
        this.wechatIds = wechatIds;
    }

    public void setWechatIds(List<String> wechatIds) {
        this.wechatIds = wechatIds;
    }

    @Override
    public void send(String message) {
        //TODO 发送微信
    }
}
