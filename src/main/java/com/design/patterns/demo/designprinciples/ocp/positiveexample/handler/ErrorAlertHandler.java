package com.design.patterns.demo.designprinciples.ocp.positiveexample.handler;

import com.design.patterns.demo.designprinciples.ocp.repository.Notification;
import com.design.patterns.demo.designprinciples.ocp.enums.NotificationEmergencyLevel;
import com.design.patterns.demo.designprinciples.ocp.positiveexample.request.ApiStatInfo;
import com.design.patterns.demo.designprinciples.ocp.rule.AlertRule;

/**
 * ErrorAlertHandler类
 *
 * @author wangjixue
 * @date 1/2/22 3:22 PM
 */
public class ErrorAlertHandler extends AlertHandler{

    public ErrorAlertHandler(AlertRule rule, Notification notification) {
        super(rule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {
        if(apiStatInfo.getErrorCount() > rule.getMatchedRule(apiStatInfo.getApi()).getMaxErrorCount()){
            notification.notify(NotificationEmergencyLevel.SEVERE, "ErrorCount exceeded threshold.");
        }
    }
}
