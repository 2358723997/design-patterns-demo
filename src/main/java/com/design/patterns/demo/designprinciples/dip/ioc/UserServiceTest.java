package com.design.patterns.demo.designprinciples.dip.ioc;

/**
 * UserServiceTest类
 *
 *
 * @author wangjixue
 * @date 1/2/22 6:14 PM
 */
public class UserServiceTest {
    public static boolean doTest(){
        return false;
    }

    //代码流程都由程序员来控制
    public static void main(String[] args) {//注意：这部分逻辑可以放到框架中
        if(doTest()){
            System.err.println("Test successed!");
        }else {
            System.err.println("Test failed!");
        }
    }
}
