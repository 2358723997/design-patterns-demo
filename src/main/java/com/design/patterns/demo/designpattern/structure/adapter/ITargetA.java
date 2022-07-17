package com.design.patterns.demo.designpattern.structure.adapter;

/**
 * ITarget接口
 *
 * 适配器模式有两种实现方式：类适配器和对象适配器。
 *
 * 类适配器使用继承关系来实现；
 * 对象适配器使用组合关系来实现；
 *
 * ITarget 表示要转化成的接口定义。
 * Adaptee 是一组不兼容ITarget接口定义的接口，
 * Adaptor 将Adaptee转化成一组符合ITarget接口定义的接口。
 *
 * 针对这两种实现方式，在实际的开发中，到底该如何选择使用哪一种呢？
 *
 * 判断的标准主要有两个，一个是Adaptee接口的个数，另一个是Adaptee和ITarget的契合程度。
 *
 * 如果Adaptee接口并不多，那两种实现方式都可以。
 * 如果Adaptee接口很多，而且Adaptee和ITarget接口定义大部分都相同，那我们推荐使用类适配器，
 * 因为Adaptor复用父类Adaptee的接口，比起对象适配器的实现方式，Adaptor的代码量要少一些。
 *
 * 如果Adaptee接口很多，而且Adaptee和ITarget接口定义大部分都不相同，那我们推荐使用对象适配器，
 * 因为组合结构相对于继承更加灵活。
 *
 *
 * @author wangjixue
 * @date 7/17/22 5:06 PM
 */
//类适配器：基于继承
public interface ITargetA {
    void f1();

    void f2();

    void fc();
}

class Adaptee {
    public void fa() {}

    public void fb() {}

    public void fc() {}
}

class ClassAdaptor extends Adaptee implements ITargetA{

    @Override
    public void f1() {
        super.fa();
    }

    @Override
    public void f2() {
        // 重新实现f2
    }


     // 这里fc()不需要实现，直接继承自Adaptee，这是跟对象适配器最大的不同点

}

//对象适配器，基于组合
interface ITargetB{
    void f1();

    void f2();

    void fc();
}

class InstanceAdaptor implements ITargetB{
    private Adaptee adaptee;

    public InstanceAdaptor(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void f1() {
        //委托给adaptee
        adaptee.fa();
    }

    @Override
    public void f2() {
        // 重新实现f2
    }

    @Override
    public void fc() {
        //委托给adaptee
        adaptee.fc();
    }
}
