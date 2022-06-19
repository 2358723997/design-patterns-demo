package com.design.patterns.demo.designpattern.create.singleton.v1;

/**
 * UserController类
 *
 * @author wangjixue
 * @date 6/19/22 10:48 PM
 */
public class UserController {
    private Logger logger = new Logger();

    public void login(String userName, String password){
        //省略业务
        logger.log(userName + "is logined!");
    }
}
