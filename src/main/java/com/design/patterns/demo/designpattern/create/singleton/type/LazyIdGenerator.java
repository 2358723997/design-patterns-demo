package com.design.patterns.demo.designpattern.create.singleton.type;

import java.util.concurrent.atomic.AtomicLong;

/**
 * IdGenerator唯一递增ID生成器
 *
 * 赖汉式单例
 *
 * 懒汉式相对于饿汉式的优势是支持延迟加载。这种实现方式会导致频繁加锁、释放锁，
 * 以及并发度低等问题，频繁的调用会产生性能瓶颈（不推荐使用）。
 *
 * @author wangjixue
 * @date 6/19/22 11:43 PM
 */
public class LazyIdGenerator {
    //CAS 设计思想，适用于并发不高的场景中。
    private AtomicLong id  = new AtomicLong(0);

    private static LazyIdGenerator instance;

    private LazyIdGenerator(){}

    public static synchronized LazyIdGenerator getInstance(){
        if(instance == null){
            instance = new LazyIdGenerator();
        }

        return instance;
    }

    public long getId(){
        return id.getAndIncrement();
    }

}
