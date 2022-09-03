package com.design.patterns.demo.designpattern.create.singleton.dimensions;

import java.util.concurrent.atomic.AtomicLong;

import javax.xml.ws.Dispatch;

import com.design.patterns.demo.coderestructure.codedecouples.RedisDistributedLock;

/**
 * ClusterSingleton类
 * 集群单例（多进程单例）
 *
 * 解决方案：
 *
 * 我们需要把这个单例对象序列化并存储到外部共享存储区（比如文件）。
 * 进程在使用这个单例对象的时候，需要先从外部共享存储区中将它读取到内存，
 * 并反序列化成对象，然后再使用，使用完成之后还需要再存储回外部共享存储区。
 * 为了保证任何时刻，在进程间都只有一份对象存在，一个进程在获取到对象之后，
 * 需要对对象加锁，避免其他进程再将其获取。
 *
 * 在进程使用完这个对象之后，还需要显式地将对象从内存中删除，并且释放对对象的加锁。
 *
 * @author wangjixue
 * @date 6/26/22 8:44 PM
 */
public class ClusterSingleton {
    private AtomicLong id = new AtomicLong(0);

    private static ClusterSingleton instance;

    private static String address;
    // 外部共享存储区
    private static SharedObjectStorage storge = new FileSharedObjectStorage(address);
    // 分布式锁
    private static DistributedLock lock = RedisDistributedLock.getSingletonIntance();

    private ClusterSingleton(){}

    public synchronized static ClusterSingleton getInstance(){
        if(instance == null){
            lock.lock();
            instance = storge.load(ClusterSingleton.class);
        }
        return instance;
    }

    public synchronized void freeInstance(){
        //存储回外部共享存储区
        lock.save(ClusterSingleton.class);
        //是否对象
        instance = null;
        //释放锁
        lock.unlock();
    }

    /**
     * 调用单例中的方法，统计调用次数
     *
     * @return
     */
    public long getId(){
        return id.incrementAndGet();
    }
}
