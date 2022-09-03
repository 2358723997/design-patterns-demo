package com.design.patterns.demo.designpattern.action.observer.v4;

import com.design.patterns.demo.designpattern.action.observer.v2.PromotionService;
import com.design.patterns.demo.designpattern.action.observer.v2.RegObserver;
import com.google.common.eventbus.Subscribe;

/**
 * RegPromotionObserver类
 *
 * @author wangjixue
 * @date 8/10/22 11:27 PM
 */
public class RegPromotionObserver implements RegObserver {
    //依赖注入
    private PromotionService promotionService;

    @Subscribe //标明某个函数能接收哪种类型的消息
    @Override
    public void handleRegSuccess(long userId) {
        promotionService.issueNewUserExperienceCash(userId);
    }
}
