package com.design.patterns.demo.designpattern.structure.proxy.staticproxy.v1;

import com.design.patterns.demo.coderestructure.programmingspecification.name.User;

/**
 * IUserController接口
 *
 * @author wangjixue
 * @date 7/5/22 11:37 PM
 */
public interface IUserController {

    public void register(User user);

    public void login(String telephone, String password);
}
