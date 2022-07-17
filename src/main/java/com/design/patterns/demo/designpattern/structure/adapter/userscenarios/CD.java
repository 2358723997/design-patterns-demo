package com.design.patterns.demo.designpattern.structure.adapter.userscenarios;

import lombok.Data;

/**
 * CD类
 *
 * 使用场景：
 * 1. 封装有缺陷的接口设计
 *
 * 假设我们依赖的外部系统在接口设计方面有缺陷（比如包含大量静态方法），
 * 引入之后会影响到我们自身代码的可测试性。为了隔离设计上的缺陷，我们
 * 希望对外部系统提供的接口进行二次封装，抽象出更好的接口设计，这个时
 * 候就可以使用适配器模式了。
 *
 * @author wangjixue
 * @date 7/17/22 5:42 PM
 */
public class CD {
    public static void staticFunction1() {}

    public void uglyNamingFunction2() {}

    public void tooManyParamsFunction3(int paramA, int ParamB, int... paramC) {}

    public void lowPerformanceFunction4() {}

}

// 使用适配器模式进行重构
interface ITraget {
    void function1();

    void function2();

    void function3(ParamsWrapperDefinition paramsWrapperDefinition);

    void function4();
}

// 注意：适配器类的命名不一定非得末尾带Adaptor
class CDAdaptor extends CD implements ITraget{

    @Override
    public void function1() {
        super.staticFunction1();
    }

    @Override
    public void function2() {
        super.uglyNamingFunction2();
    }

    @Override
    public void function3(ParamsWrapperDefinition params) {
        super.tooManyParamsFunction3(params.getParamA(),params.ParamB,params.paramC);
    }

    @Override
    public void function4() {
        super.lowPerformanceFunction4();
    }
}

@Data
class ParamsWrapperDefinition {
    int paramA;
    int ParamB;
    int paramC;
}
