package com.design.patterns.demo.designpattern.structure.decorator;

/**
 * IA类
 *
 * 第二个比较特殊的地方是：
 * 装饰器类是对功能的增强，这也是装饰器模式应用场景的一个重要特点。
 *
 * 实际上，符合“组合关系”这种代码结构的设计模式有很多，比如之前讲过
 * 的代理模式、桥接模式，还有现在的装饰器模式。尽管它们的代码结构很相
 * 似，但是每种设计模式的意图是不同的。
 * 就拿比较相似的代理模式和装饰器模式来说吧，
 *
 * 代理模式中，代理类附加的是跟原始类无关的功能，
 *
 * 而在装饰器模式中，装饰器类附加的是跟原始类相关的增强功能。
 *
 * @author wangjixue
 * @date 7/13/22 11:36 PM
 */
//下面的接口也可以替换成抽象类
//代理、装饰器模式的代码结构
public interface IA {

    void f();
}

class A implements IA {

    @Override
    public void f() {

    }
}

class AProyx implements IA {
    private IA a;

    public AProyx(IA a) {
        this.a = a;
    }

    @Override
    public void f() {
        //新添加的代理逻辑(附加的是跟原始类无关的功能，如：权限，日志打点)
        a.f();
        //新添加的代理逻辑(附加的是跟原始类无关的功能,如：权限，日志打点)
    }
}

class ADecorator implements IA {
    private IA a;

    public ADecorator(IA a) {
        this.a = a;
    }

    @Override
    public void f() {
        //功能增进去逻辑
        a.f();
        //功能增进去逻辑

    }

}
