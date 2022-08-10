package com.design.patterns.demo.designpattern.action.observer.v2;

/**
 * RegPromotionObserver类
 *
 * @author wangjixue
 * @date 8/10/22 11:27 PM
 */
public class RegNotificationObserver implements RegObserver {
    //依赖注入
    private NotificationService notificationService;

    @Override
    public void handleRegSuccess(long userId) {
        String content = "通知XXX";
        notificationService.sendMessage(userId, content);
    }
}
