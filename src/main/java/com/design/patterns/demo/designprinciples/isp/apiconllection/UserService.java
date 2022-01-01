package com.design.patterns.demo.designprinciples.isp.apiconllection;

import com.design.patterns.demo.designprinciples.srp.positiveexample.UserInfo;

/**
 * UserService接口
 *
 *  场景：用户系统提供了一组跟用户相关的 API 给其他系统使用，比如：注册、登录、获取用户信息等。现在，
 *  我们的后台管理系统要实现删除用户的功能，希望用户系统提供一个删除用户的接口。这个时候我们该如何来做呢？
 *
 *  分析：方案一：在 UserService 中新添加一个 deleteUserByCellphone() 或 deleteUserById() 接口就可以了。
 *  这个方法可以解决问题，但是也隐藏了一些安全隐患，删除用户是一个非常慎重的操作，我们只希望通过后台管理系统来执行，
 *  所以这个接口只限于给后台管理系统使用，如果在没有鉴权的情况下，加限制地被其他业务系统调用，就有可能导致误删用户。
 *
 *  方案二：在没有鉴权情况下可以从代码层面规避上述风险,具体可以参照接口隔离原则，调用者不应该强迫依赖它不需要的接口，
 *  将删除接口单独放到另外一个接口 RestrictedUserService 中，然后将 RestrictedUserService 只打包提供给后台
 *  管理系统来使用。
 *
 * @author wangjixue
 * @date 12/26/21 4:31 PM
 */

public interface UserService {

    boolean register(String cellphone, String password);

    boolean login(String cellphone, String password);

    UserInfo getUserInfoById(long id);

    UserInfo getUserInfoByCellphone(String cellphone);
}
