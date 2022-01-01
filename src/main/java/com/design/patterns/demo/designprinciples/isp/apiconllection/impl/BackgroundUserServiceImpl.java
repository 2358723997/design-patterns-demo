package com.design.patterns.demo.designprinciples.isp.apiconllection.impl;

import com.design.patterns.demo.designprinciples.isp.apiconllection.RestrictedUserService;
import com.design.patterns.demo.designprinciples.isp.apiconllection.UserService;
import com.design.patterns.demo.designprinciples.srp.positiveexample.UserInfo;

/**
 * BackgroundUserServiceImpl类
 *
 * @author wangjixue
 * @date 12/26/21 4:39 PM
 */
public class BackgroundUserServiceImpl implements UserService, RestrictedUserService {

    @Override
    public boolean deleteUserByCellphone(String cellphone) {
        return false;
    }

    @Override
    public boolean deleteUserById(long id) {
        return false;
    }

    @Override
    public boolean register(String cellphone, String password) {
        return false;
    }

    @Override
    public boolean login(String cellphone, String password) {
        return false;
    }

    @Override
    public UserInfo getUserInfoById(long id) {
        return null;
    }

    @Override
    public UserInfo getUserInfoByCellphone(String cellphone) {
        return null;
    }
}
