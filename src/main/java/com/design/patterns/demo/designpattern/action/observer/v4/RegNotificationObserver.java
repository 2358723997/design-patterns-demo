package com.design.patterns.demo.designpattern.action.observer.v4;

import com.design.patterns.demo.designpattern.action.observer.v2.NotificationService;
import com.design.patterns.demo.designpattern.action.observer.v2.RegObserver;
import com.google.common.eventbus.Subscribe;

/**
 * RegPromotionObserver类
 *
 * @author wangjixue
 * @date 8/10/22 11:27 PM
 */
public class RegNotificationObserver implements RegObserver {
    //依赖注入
    private NotificationService notificationService;

    @Subscribe
    @Override
    public void handleRegSuccess(long userId) {
        String content = "通知XXX";
        notificationService.sendMessage(userId, content);
    }
}
