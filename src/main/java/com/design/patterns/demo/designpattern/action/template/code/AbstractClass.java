package com.design.patterns.demo.designpattern.action.template.code;

/**
 * AbstractClass类
 *
 * 模板方法模式：
 *
 * 模板方法模式在一个方法中定义一个算法骨架，并将某些步骤推迟到子类中实现。
 * 模板方法模式可以让子类在不改变算法整体结构的情况下，重新定义算法中的某
 * 些步骤。
 *
 * 这里的“算法”，我们可以理解为广义上的“业务逻辑”，并不特指数据结构和算法
 * 中的“算法”。这里的算法骨架就是“模板”，包含算法骨架的方法就是“模板方法”
 * ，这也是模板方法模式名字的由来。
 *
 * 原理很简单，代码实现就更加简单，我写了一个示例代码，
 *
 * 如下所示。templateMethod() 函数定义为 final，是为了避免子类重写它。
 * method1() 和 method2() 定义为 abstract，是为了强迫子类去实现。
 *
 * @author wangjixue
 * @date 2022/9/3 2:06 PM
 */
public abstract class AbstractClass {

    public final void templateMethod(){

        method1();

        method2();
    }

    protected abstract void method1();

    protected abstract void method2();
}
