package com.design.patterns.demo.designpattern.action.observer.v2;

import com.design.patterns.demo.designprinciples.isp.apiconllection.UserService;

/**
 * UserController01类
 *
 * 需求：假设我们在开发一个 P2P 投资理财系统，用户注册成功之后，
 * 我们会给用户发放投资体验金。
 **
 * @author wangjixue
 * @date 8/10/22 11:16 PM
 */
public class UserController01 {
    //依赖注入
    private UserService userService;
    private PromotionService promotionService;

    public long register(String telephone, String password){
        userService.register(telephone, password);
        promotionService.issueNewUserExperienceCash(1l);
        return 1l;
    }
}
