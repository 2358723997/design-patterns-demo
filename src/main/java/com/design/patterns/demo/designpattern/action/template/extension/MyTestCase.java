package com.design.patterns.demo.designpattern.action.template.extension;

import org.junit.Assert;
import org.junit.Test;

/**
 * MyTestCase类
 *
 * 跟 Java Servlet 类似，JUnit 框架也通过模板模式
 * 提供了一些功能扩展点（setUp()、tearDown() 等），
 * 让框架用户可以在这些扩展点上扩展功能。
 *
 * 在使用 JUnit 测试框架来编写单元测试的时候，我们编写
 * 的测试类都要继承框架提供的 TestCase 类。
 *
 * 在 TestCase 类中，runBare()函数是模板方法，它定义了
 * 执行测试用例的整体流程：
 *  先执行 setUp() 做些准备工作，
 *  然后执行 runTest() 运行真正的测试代码，
 *  最后执行 tearDown() 做扫尾工作。
 *
 *  TestCase 类的具体代码如下所示。
 *  尽管 setUp()、tearDown() 并不是抽象函数还提供了默认的实现，
 *  不强制子类去重新实现，但是这部分也是可以在子类中定制的，所以也
 *  符合模板模式的定义。
 *
 * @author wangjixue
 * @date 2022/9/3 3:35 PM
 */
public abstract class MyTestCase extends Assert implements Test {
    public void runBare() throws Throwable{
        Throwable exception = null;
        setUp();
        try {
            runTest();
        }catch (Throwable running){
            exception  = running;
        }finally {
            try{
                tearDown();
            }catch (Throwable tearingDwon){
                if(exception == null){
                    exception = tearingDwon;
                }
            }
        }
        if(exception != null){
            throw exception;
        }
    }

    private void runTest() {
    }

    protected void tearDown() {

    }

    protected void setUp() {
    }
}
