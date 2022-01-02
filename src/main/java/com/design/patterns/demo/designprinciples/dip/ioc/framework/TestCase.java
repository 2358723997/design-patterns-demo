package com.design.patterns.demo.designprinciples.dip.ioc.framework;

/**
 * TestCase类
 *
 * @author wangjixue
 * @date 1/2/22 6:19 PM
 */
public abstract class TestCase {

    public void run() {
        if (doTest()) {
            System.out.println("Test succeed.");
        }else{
            System.out.println("Test failed.");
        }
    }
    //框架预留的扩展点
    public abstract boolean doTest();
}
