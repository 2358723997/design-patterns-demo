package com.design.patterns.demo.designpattern.structure.bridge.newcase;

/**
 * UrgencyNotification类
 *
 * @author wangjixue
 * @date 7/13/22 12:09 AM
 */
public class UrgencyNotification extends Notification{

    public UrgencyNotification(MsgSender sender) {
        super(sender);
    }

    @Override
    public void notifity(String message) {

    }
}
