package com.design.patterns.demo.designpattern.action.observer.v3;

import com.design.patterns.demo.designpattern.action.observer.v2.RegObserver;
import com.design.patterns.demo.designprinciples.isp.apiconllection.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * UserController类
 *
 * 异步非阻塞观察者模式
 *
 * 实现二：在 UserController 的 register() 函数中使用线程池来执行每个观察者的 handleRegSuccess() 函数
 *
 * @author wangjixue
 * @date 2022/9/3 10:23 AM
 */
public class UserController {
    //依赖注入
    private UserService userService;
    private List<RegObserver> regObservers = new ArrayList<>();
    private Executor executor;

    public UserController(Executor executor) {
        this.executor = executor;
    }

    public void setRegObservers(List<RegObserver> regObservers) {
        regObservers.addAll(regObservers);
    }

    public Long register(String telephone, String password){
        Long userId = -1L;
        userService.register(telephone, password);
        for (RegObserver regObserver : regObservers) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    regObserver.handleRegSuccess(userId);
                }
            });
        }
        return userId;
    }
}
