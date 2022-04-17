package com.design.patterns.demo.coderestructure.codedecouples.v1;

import com.design.patterns.demo.coderestructure.codedecouples.RedisDistributedLock;

/**
 * TransactionLock类
 *
 * 优化点二
 *
 * @author wangjixue
 * @date 4/17/22 11:43 PM
 */
public class TransactionLock {

    public boolean lock(String lockName){
        return RedisDistributedLock.getSingletonIntance().lockTransction(lockName);
    }

    public boolean unlock(String lockName){
        return RedisDistributedLock.getSingletonIntance().unlockTransction(lockName);
    }
}
