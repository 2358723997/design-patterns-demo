package com.design.patterns.demo.designprinciples.ocp.positiveexample.handler;

import com.design.patterns.demo.designprinciples.ocp.repository.Notification;
import com.design.patterns.demo.designprinciples.ocp.enums.NotificationEmergencyLevel;
import com.design.patterns.demo.designprinciples.ocp.positiveexample.request.ApiStatInfo;
import com.design.patterns.demo.designprinciples.ocp.rule.AlertRule;

/**
 * TpsAlertHandler类
 *
 * @author wangjixue
 * @date 1/2/22 3:20 PM
 */
public class TpsAlertHandler extends AlertHandler{

    public TpsAlertHandler(AlertRule rule, Notification notification) {
        super(rule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {
        long tps = apiStatInfo.getRequestCount()/apiStatInfo.getDurationOfSeconds();
        if(tps > rule.getMatchedRule(apiStatInfo.getApi()).getMaxTps()){
            notification.notify(NotificationEmergencyLevel.URGENCY,"TPS exceeded threshold.");
        }
    }
}
