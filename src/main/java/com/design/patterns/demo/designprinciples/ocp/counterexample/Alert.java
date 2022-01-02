package com.design.patterns.demo.designprinciples.ocp.counterexample;

import com.design.patterns.demo.designprinciples.ocp.repository.Notification;
import com.design.patterns.demo.designprinciples.ocp.rule.AlertRule;
import com.design.patterns.demo.designprinciples.ocp.enums.NotificationEmergencyLevel;

/**
 * Alert 存储告警规则，可以自由设置。
 * Notification 是告警通知类，支持邮件、短信、微信、手机等多种通知渠道。
 * NotificationEmergencyLevel 表示通知的紧急程度，包括 SEVERE（严重）、
 * URGENCY（紧急）、NORMAL（普通）、TRIVIAL（无关紧要），不同的紧急程度对应不同的发送渠道。
 *
 * API接口监控告警需求描述：
 *
 * 现有功能：1.当接口的 TPS 超过某个预先设置的最大值时，触发告警，通知接口的相关负责人或者团队。
 * 2.当接口请求出错数大于某个最大允许值时，触发告警，通知接口的相关负责人或者团队。
 * 新增需求：当每秒钟接口超时请求个数，超过某个预先设置的最大阈值时，我们也要触发告警发送通知
 */
public class Alert {

    private AlertRule rule;

    private Notification notification;

    public Alert(AlertRule rule, Notification notification) {
        this.rule = rule;
        this.notification = notification;
    }

    //逻辑如下：
    // 改动一：添加参数timeoutCount
    //TODO: 直接改动存在两个问题：1.调用这个接口的代码都要做相应的修改；2.相应的单元测试都需要修改
    public void check(String api, long requestCount, long errorCount, long durationOfSeconds, long timeoutCount){
        long tps = requestCount/durationOfSeconds;
        if(tps > rule.getMatchedRule(api).getMaxTps()){
            notification.notify(NotificationEmergencyLevel.URGENCY,"TPS exceeded threshold.");
        }
        if(errorCount > rule.getMatchedRule(api).getMaxErrorCount()){
            notification.notify(NotificationEmergencyLevel.SEVERE, "ErrorCount exceeded threshold.");
        }
        //改动二：添加接口超时处理逻辑
        long timeoutTps = timeoutCount/durationOfSeconds;
        if(timeoutTps > rule.getMatchedRule(api).getMaxTimeoutTps()){
            notification.notify(NotificationEmergencyLevel.URGENCY, "TimeoutTps exceeded threshold.");
        }
    }
}
