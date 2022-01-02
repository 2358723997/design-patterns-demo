package com.design.patterns.demo.designprinciples.ocp.positiveexample.handler;

import com.design.patterns.demo.designprinciples.ocp.repository.Notification;
import com.design.patterns.demo.designprinciples.ocp.enums.NotificationEmergencyLevel;
import com.design.patterns.demo.designprinciples.ocp.positiveexample.request.ApiStatInfo;
import com.design.patterns.demo.designprinciples.ocp.rule.AlertRule;

/**
 * TimeoutAlertHander类
 *
 * @author wangjixue
 * @date 1/2/22 3:24 PM
 */
public class TimeoutAlertHander extends AlertHandler{

    public TimeoutAlertHander(AlertRule rule, Notification notification) {
        super(rule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {
        long timeoutTps = apiStatInfo.getTimeoutCount()/apiStatInfo.getDurationOfSeconds();
        if(timeoutTps > rule.getMatchedRule(apiStatInfo.getApi()).getMaxTimeoutTps()){
            notification.notify(NotificationEmergencyLevel.URGENCY, "TimeoutTps exceeded threshold.");
        }
    }
}
