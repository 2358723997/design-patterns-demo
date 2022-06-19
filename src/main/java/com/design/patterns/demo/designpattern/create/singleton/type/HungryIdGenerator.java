package com.design.patterns.demo.designpattern.create.singleton.type;

import java.util.concurrent.atomic.AtomicLong;

/**
 * IdGenerator唯一递增ID生成器
 *
 * 饿汉式单例
 *
 * 饿汉式的实现方式，在类加载的期间，就已经将 instance 静态实例初始化好了，
 * 所以，instance 实例的创建是线程安全的。不过，这样的实现方式不支持延迟加载实例。
 *
 * @author wangjixue
 * @date 6/19/22 11:43 PM
 */
public class HungryIdGenerator {
    //CAS 设计思想，适用于并发不高的场景中。
    private AtomicLong id  = new AtomicLong(0);

    private static final HungryIdGenerator instance = new HungryIdGenerator();

    private HungryIdGenerator(){}

    public static HungryIdGenerator getInstance(){
        return instance;
    }

    public long getId(){
        return id.getAndIncrement();
    }

}
