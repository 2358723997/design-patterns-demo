package com.design.patterns.demo.designpattern.create.singleton.type;

import java.util.concurrent.atomic.AtomicLong;

/**
 * IdGenerator唯一递增ID生成器
 *
 * 静态内部类式单例
 *
 * 利用 Java 的静态内部类来实现单例。这种实现方式，既支持延迟加载，也支持高并发，
 * 实现起来也比双重检测简单（推荐）。
 *
 * @author wangjixue
 * @date 6/19/22 11:43 PM
 */
public class StaticInnerClassIdGenerator {
    private static class SingletonHolder {
        private static final StaticInnerClassIdGenerator instance = new StaticInnerClassIdGenerator();
    }

    //CAS 设计思想，适用于并发不高的场景中。
    private AtomicLong id = new AtomicLong(0);

    private StaticInnerClassIdGenerator() {}

    public static StaticInnerClassIdGenerator getInstance() {

        return SingletonHolder.instance;
    }

    public long getId() {
        return id.getAndIncrement();
    }

}
