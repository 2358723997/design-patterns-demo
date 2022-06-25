package com.design.patterns.demo.designpattern.create.singleton.counterexample;

/**
 * Singleton类
 *
 * 多参数单例类
 * 方案一：将参数放到 getIntance() 方法中
 *
 * @author wangjixue
 * @date 6/25/22 11:17 PM
 */
public class SingletonB {
    private static SingletonB instance;

    private final int paramA;

    private final int paramB;

    private SingletonB(int paramA, int paramB) {
        this.paramA = paramA;
        this.paramB = paramB;
    }

    public synchronized static SingletonB getInstance(int paramA, int paramB) {
        if(instance == null){
            instance = new SingletonB(paramA, paramB);
        }
        if (paramA != instance.paramA || paramB != instance.paramB) {
            throw new RuntimeException("Instance already create.");
        }
        return instance;
    }
}
