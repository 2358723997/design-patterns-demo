package com.design.patterns.demo.designpattern.action.observer.v2;

/**
 * RegPromotionObserver类
 *
 * @author wangjixue
 * @date 8/10/22 11:27 PM
 */
public class RegPromotionObserver implements RegObserver{
    //依赖注入
    private PromotionService promotionService;

    @Override
    public void handleRegSuccess(long userId) {
        promotionService.issueNewUserExperienceCash(userId);
    }
}
