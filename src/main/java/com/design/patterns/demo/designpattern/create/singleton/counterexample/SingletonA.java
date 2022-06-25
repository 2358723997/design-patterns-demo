package com.design.patterns.demo.designpattern.create.singleton.counterexample;

/**
 * Singleton类
 *
 * 多参数单例类
 * 方案一：调用 init()函数，创建完实例的时候传递参数。
 * 需要注意的是，我们在使用这个单例类的时候，要先调用 init() 方法，然后
 * 才能调用 getInstance() 方法，否则代码会抛出异常
 *
 * @author wangjixue
 * @date 6/25/22 11:17 PM
 */
public class SingletonA {
    private static SingletonA instance;

    private final int paramA;

    private final int paramB;

    private SingletonA(int paramA, int paramB) {
        this.paramA = paramA;
        this.paramB = paramB;
    }

    public static SingletonA getInstance(){
        if(instance == null){
            throw new RuntimeException("First run init().");
        }
        return instance;
    }

    public static void init(int paramA, int paramB) {
        if (instance != null) {
            throw new RuntimeException("Replace create instance.");
        }
        synchronized (SingletonA.class) {
            if (instance == null) {
                instance = new SingletonA(paramA, paramB);
            }
        }

    }
}
