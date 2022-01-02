package com.design.patterns.demo.designprinciples.ocp.positiveexample.handler;

import com.design.patterns.demo.designprinciples.ocp.repository.Notification;
import com.design.patterns.demo.designprinciples.ocp.positiveexample.request.ApiStatInfo;
import com.design.patterns.demo.designprinciples.ocp.rule.AlertRule;

/**
 * AlertHandler类
 *
 * @author wangjixue
 * @date 1/2/22 3:14 PM
 */
public abstract class AlertHandler {

    protected AlertRule rule;

    protected Notification notification;

    public AlertHandler(AlertRule rule, Notification notification) {
        this.rule = rule;
        this.notification = notification;
    }

    public abstract void check(ApiStatInfo apiStatInfo);
}
