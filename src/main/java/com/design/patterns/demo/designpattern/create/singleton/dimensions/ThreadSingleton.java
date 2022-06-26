package com.design.patterns.demo.designpattern.create.singleton.dimensions;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ThreadSingleton类
 *
 * 线程维度下单例实现类：ThreadLocal
 *
 * 实现方案：在代码中，我们通过一个 HashMap 来存储对象，其中 key是线程ID，value是对象。
 * 这样我们就可以做到，不同的线程对应不同的对象，同一个线程只能对应一个对象。
 *
 *
 * @author wangjixue
 * @date 6/26/22 8:30 PM
 */
public class ThreadSingleton {
    private AtomicLong id = new AtomicLong(0);

    private static final ConcurrentHashMap<Long,ThreadSingleton> CACHE_MAP = new ConcurrentHashMap();

    private ThreadSingleton(){

    }

    private static ThreadSingleton getInstance(){
        long cureateThreadId = Thread.currentThread().getId();
        CACHE_MAP.putIfAbsent(cureateThreadId,new ThreadSingleton());
        return CACHE_MAP.get(cureateThreadId);
    }

    /**
     * 记录调用次数
     *
     * @return
     */
    public long getId(){
        return id.incrementAndGet();
    }
}
