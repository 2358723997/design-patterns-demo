package com.design.patterns.demo.designprinciples.ocp.positiveexample.context;

import com.design.patterns.demo.designprinciples.ocp.positiveexample.handler.ErrorAlertHandler;
import com.design.patterns.demo.designprinciples.ocp.positiveexample.handler.TimeoutAlertHander;
import com.design.patterns.demo.designprinciples.ocp.positiveexample.handler.TpsAlertHandler;
import com.design.patterns.demo.designprinciples.ocp.repository.Notification;
import com.design.patterns.demo.designprinciples.ocp.positiveexample.Alert;
import com.design.patterns.demo.designprinciples.ocp.repository.impl.RocketMQNotification;
import com.design.patterns.demo.designprinciples.ocp.rule.AlertRule;

/**
 * ApplicationContext是一个单例类，
 * 负责 Alert 的创建、组装（alertRule 和 notification 的依赖注入）、
 * 初始化（添加 handlers）工作。
 *
 * @author wangjixue
 * @date 1/2/22 3:34 PM
 */
public class ApplicationContext {
    private AlertRule alertRule;
    private Notification notification;
    private Alert alert;

    public void initializeBeans() {
        alertRule = new AlertRule();
        notification = new RocketMQNotification();
        alert = new Alert();

        alert.addHandler(new TpsAlertHandler(alertRule,notification));
        alert.addHandler(new ErrorAlertHandler(alertRule,notification));
        alert.addHandler(new TimeoutAlertHander(alertRule,notification));
    }

    private static final ApplicationContext instance = new ApplicationContext();

    private ApplicationContext() {
        initializeBeans();
    }

    public static ApplicationContext getInstance(){

        return instance;
    }

    public Alert getAlert() {
        return alert;
    }
}
