package com.design.patterns.demo.designpattern.create.singleton.type;

import java.util.concurrent.atomic.AtomicLong;

/**
 * EnumerateIdGenerator枚举
 *
 * 枚举式单例
 *
 * 最简单的实现方式，基于枚举类型的单例实现。
 * 这种实现方式通过 Java 枚举类型本身的特性，保证了实例创建的线程安全性和实例的唯一性（推荐）。
 * @author wangjixue
 * @date 6/20/22 12:09 AM
 */
public enum EnumerateIdGenerator {
    INSTANCE;

    //CAS 设计思想，适用于并发不高的场景中。
    private AtomicLong id = new AtomicLong(0);

    public long getId(){
        return id.getAndIncrement();
    }
}
