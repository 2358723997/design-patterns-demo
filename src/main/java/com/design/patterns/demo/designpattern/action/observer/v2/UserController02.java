package com.design.patterns.demo.designpattern.action.observer.v2;

import java.util.ArrayList;
import java.util.List;

import com.design.patterns.demo.designprinciples.isp.apiconllection.UserService;

/**
 * UserController02类
 *
 * 虽然注册接口做了两件事情，注册和发放体验金，违反单一职责原则，但是，
 * 如果没有扩展和修改的需求，现在的代码实现是可以接受的。如果非得用观察
 * 者模式，就需要引入更多的类和更加复杂的代码结构，反倒是一种过度设计。
 *
 * 相反，如果需求频繁变动，比如，用户注册成功之后，不再发放体验金，而是
 * 改为发放优惠券，并且还要给用户发送一封“欢迎注册成功”的站内信。这种情
 * 况下，我们就需要频繁地修改 register() 函数中的代码，违反开闭原则。
 * 而且，如果注册成功之后需要执行的后续操作越来越多，那 register() 函
 * 数的逻辑会变得越来越复杂，也就影响到代码的可读性和可维护性。
 *
 * 这个时候，观察者模式就能派上用场了。利用观察者模式，我对上面的代码进行了重构。
 *
 * @author wangjixue
 * @date 8/10/22 11:16 PM
 */
public class UserController02 {
    //依赖注入
    private UserService userService;
    //用于缓存观察者
    private List<RegObserver> regObservers = new ArrayList<>();

    //一次性设置好，之后不可动态修改
    //TODO 可优化
    public void setRegObservers(List<RegObserver> regObservers) {
        this.regObservers.addAll(regObservers);
    }

    public long register(String telephone, String password){
        userService.register(telephone, password);
        for (RegObserver regObserver : regObservers) {
            regObserver.handleRegSuccess(1l);
        }
        return 1l;
    }
}
