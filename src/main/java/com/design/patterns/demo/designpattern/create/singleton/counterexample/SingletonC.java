package com.design.patterns.demo.designpattern.create.singleton.counterexample;

/**
 * Singleton类
 *
 * 多参数单例类
 * 方案一：将参数放到另外一个全局变量中。具体的代码实现如下。
 * Config 是一个存储了 paramA 和 paramB 值的全局变量。
 *
 * @author wangjixue
 * @date 6/25/22 11:17 PM
 */
public class SingletonC {
    private static SingletonC instance;

    private final int paramA;

    private final int paramB;

    private SingletonC() {
        this.paramA = Config.SINGLETONC_PARAMA;
        this.paramB = Config.SINGLETONC_PARAMB;
    }

    public static SingletonC getInstance(){
        if(instance == null){
            synchronized (SingletonC.class){
                if(instance == null){
                    instance = new SingletonC();
                }
            }
        }
        return instance;
    }
}
