package com.design.patterns.demo.designprinciples.ocp.repository.impl;

import com.design.patterns.demo.designprinciples.ocp.enums.NotificationEmergencyLevel;
import com.design.patterns.demo.designprinciples.ocp.repository.Notification;

/**
 * RocketMQNotification类
 *
 * @author wangjixue
 * @date 1/2/22 3:40 PM
 */
public class RocketMQNotification implements Notification {

    @Override
    public void notify(NotificationEmergencyLevel level, String message) {

    }
}
