package com.design.patterns.demo.coderestructure.programmingspecification.art;

import lombok.Data;

/**
 * User类
 *
 * @author wangjixue
 * @date 4/20/22 11:08 PM
 */
public class User {

    public User getUser(String userName, String telephone, String email) {
        return null;
    }
    // 根据函数职责单一的特性，将函数拆分成多个
    public User getUsetByName(String name) {return null;}

    public User getUsetByTelephone(String Telephone) {return null;}

    public User getUsetByEmail(String email) {return null;}

    // 场景二： 将参数封装为对象的方式
    public User getUser(UserReqest userReqest) {
        return null;
    }
}

@Data
class UserReqest{
    private String userName;
    private String telephone;
    private String email;
}
