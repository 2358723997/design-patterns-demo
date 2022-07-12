package com.design.patterns.demo.designpattern.structure.bridge.newcase;

/**
 * SevereNotification类
 *
 * @author wangjixue
 * @date 7/13/22 12:06 AM
 */
public class SevereNotification extends Notification{

    public SevereNotification(MsgSender sender) {
        super(sender);
    }

    @Override
    public void notifity(String message) {
        sender.send(message);
    }
}
