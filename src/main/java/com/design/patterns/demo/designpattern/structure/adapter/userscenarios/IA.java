package com.design.patterns.demo.designpattern.structure.adapter.userscenarios;

/**
 * IA接口
 *
 * 使用场景3：替换依赖的外部系统
 *
 * 当我们把项目中依赖的一个外部系统替换为另一个外部系统的时候，
 * 利用适配器模式，可以减少对代码的改动
 *
 *
 * @author wangjixue
 * @date 7/17/22 6:17 PM
 */
//外部系统A
public interface IA {
    void fa();
}

class A implements IA{

    @Override
    public void fa() {

    }
}

//外部系统A使用示例
class Demo{
    private IA a;

    public Demo(IA a) {
        this.a = a;
    }

    public static void main(String[] args) {
        Demo demo = new Demo(new A());
        // 借助BAdaptor，Demo的代码中，调用IA接口的地方都无需改动，
        // 只需要将BAdaptor如下注入到Demo即可。
        demo = new Demo(new BAdaptor(new B() {
            @Override
            public void fb() {

            }
        }));
    }
}

//外部系统B
interface B{
    void fb();
}

//将外部系统A替换成外部系统B
class BAdaptor implements IA{
    private B b;

    public BAdaptor(B b) {
        this.b = b;
    }

    @Override
    public void fa() {
        b.fb();
    }
}
