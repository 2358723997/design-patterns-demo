package com.design.patterns.demo.coderestructure.codedecouples;


import org.redisson.Redisson;
import org.redisson.RedissonLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

/**
 * RedisDistributedLock类
 *
 * @author wangjixue
 * @date 4/17/22 10:05 PM
 */
public class RedisDistributedLock{

    private static final RedissonClient redission = Redisson.create();
    private static RedisDistributedLock instance = new RedisDistributedLock();


    private RedisDistributedLock(){

    }

    public static RedisDistributedLock getSingletonIntance() {
        return instance;
    }

    public boolean lockTransction(String lockName){
        RLock lock = redission.getLock(lockName);
        if(lock != null){
            lock.tryLock();
            return true;
        }
        return false;
    }


        public boolean unlockTransction(String lockName){
        RLock lock = redission.getLock(lockName);
        if( lock != null){
            lock.unlock();
            return true;
        }

        return false;
    }
}
