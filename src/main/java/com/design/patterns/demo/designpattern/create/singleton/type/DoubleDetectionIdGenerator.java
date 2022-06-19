package com.design.patterns.demo.designpattern.create.singleton.type;

import java.util.concurrent.atomic.AtomicLong;

/**
 * IdGenerator唯一递增ID生成器
 *
 * 双重检测式单例
 *
 * 双重检测实现方式既支持延迟加载、又支持高并发的单例实现方式。
 * 只要 instance 被创建之后，再调用 getInstance() 函数都不会进入到加锁逻辑中。
 * 所以，这种实现方式解决了懒汉式并发度低的问题。
 *
 * @author wangjixue
 * @date 6/19/22 11:43 PM
 */
public class DoubleDetectionIdGenerator {
    //CAS 设计思想，适用于并发不高的场景中。
    private AtomicLong id  = new AtomicLong(0);

    // 问题：是否会存在指令重排序导致的IdGenerator对象被new出来，并且赋值给instance之后，
    // 还没来得及初始化，就被另一个线程使用了的情况，目前高版本的JDk通过将对象new操作和初始化操作设计为
    // 原子操作来解决了该问题。
    private static DoubleDetectionIdGenerator instance;

    private DoubleDetectionIdGenerator(){}

    public static DoubleDetectionIdGenerator getInstance(){
        if(instance == null){
            synchronized(DoubleDetectionIdGenerator.class){
                if(instance == null){
                    instance = new DoubleDetectionIdGenerator();
                }
            }

        }

        return instance;
    }

    public long getId(){
        return id.getAndIncrement();
    }

}
