package com.design.patterns.demo.designprinciples.dip.ioc.framework;

/**
 * IocUserServiceTest类
 *
 * @author wangjixue
 * @date 1/2/22 6:25 PM
 */
public class IocUserServiceTest extends TestCase{

    @Override
    public boolean doTest() {
        return false;
    }

    public static void main(String[] args) {
        // 注册操作还可以通过配置的方式来实现，不需要程序员显示调用register()
        JunitApplication.register(new IocUserServiceTest());
    }
}
