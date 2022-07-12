package com.design.patterns.demo.designpattern.structure.bridge.newcase;

/**
 * NormalNotification类
 *
 * @author wangjixue
 * @date 7/13/22 12:06 AM
 */
public class NormalNotification extends Notification{

    public NormalNotification(MsgSender sender) {
        super(sender);
    }

    @Override
    public void notifity(String message) {
        sender.send(message);
    }
}
