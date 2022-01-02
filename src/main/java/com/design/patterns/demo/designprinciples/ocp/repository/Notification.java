package com.design.patterns.demo.designprinciples.ocp.repository;

import com.design.patterns.demo.designprinciples.ocp.enums.NotificationEmergencyLevel;

/**
 * Notification类
 *
 * @author wangjixue
 * @date 1/2/22 2:30 PM
 */
public interface Notification {

    public void notify(NotificationEmergencyLevel level,String message);
}
