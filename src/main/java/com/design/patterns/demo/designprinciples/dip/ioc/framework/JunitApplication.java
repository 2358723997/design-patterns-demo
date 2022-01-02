package com.design.patterns.demo.designprinciples.dip.ioc.framework;

import java.util.ArrayList;
import java.util.List;

/**
 * JunitApplication类
 *
 * 框架提供了一个可扩展的代码骨架，用来组装对象、管理整个执行流程。程序员利用框架进行开发的时候，
 * 只需要往预留的扩展点上，添加跟自己业务相关的代码，就可以利用框架来驱动整个程序流程的执行。
 *
 * 这里的“控制”指的是对程序执行流程的控制，而“反转”指的是在没有使用框架之前，程序员自己控制整个
 * 程序的执行。在使用框架之后，整个程序的执行流程可以通过框架来控制。流程的控制权从程序员“反转”到了框架。
 *
 * @author wangjixue
 * @date 1/2/22 6:22 PM
 */
public class JunitApplication {

    private static final List<TestCase> testCases = new ArrayList<>();

    public static void register(TestCase testCase){
        testCases.add(testCase);
    }

    //程序启动入口
    public static final void main(String[] args) {
        for (TestCase testCase : testCases) {
            testCase.run();
        }
    }
}
