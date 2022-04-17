package com.design.patterns.demo.coderestructure.codedecouples.v1;

import javax.transaction.InvalidTransactionException;

import com.design.patterns.demo.coderestructure.codedecouples.RedisDistributedLock;
import com.design.patterns.demo.coderestructure.codedecouples.STATUS;
import com.design.patterns.demo.coderestructure.codedecouples.WalletRpcService;
import com.design.patterns.demo.coderestructure.codedecouples.utils.IdGenerator;
import lombok.Getter;

/**
 * TransactionNew类是抽象简化之后的一个电商系统的交易类，用来记录每笔订单交易的情况。
 *
 * @author wangjixue
 * @date 4/17/22 9:47 PM
 */
@Getter
public class TransactionNew {

    private static final long FOURTEEN_DAYS = 3600 * 24 * 14 * 1000l;
    private String id;
    private Long buyerId;
    private Long sellerId;
    private Long productId;
    private String orderId;
    private Long createTimestamp;
    private Double amount;
    private STATUS status;
    private String walletTransactionId;
    //优化点1：依赖注入
    private WalletRpcService walletRpcService;
    //优化点2：依赖注入
    private TransactionLock transactionLock;

    public TransactionNew(String preAssignedId, Long buyerId, Long sellerId, Long productId, String orderId) {
        //优化点4
        //if (preAssignedId != null && !preAssignedId.isEmpty()) {
        //    this.id = preAssignedId;
        //} else {
        //    this.id = IdGenerator.generateTransactionId();
        //}
        //
        //if (this.id.startsWith("t_")) {
        //    this.id = "t_" + this.id;
        //}
        fillTransactionId(preAssignedId);
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.productId = productId;
        this.orderId = orderId;
        this.status = STATUS.TO_BE_EXECUTD;
        this.createTimestamp = System.currentTimeMillis();
    }

    /**
     * 构造函数中并非只包含简单赋值操作。交易id 的赋值逻辑稍微复杂
     *
     * 我们可以把id 赋值这部分逻辑单独抽象到fillTransactionId函数中进行测试。
     *
     * @param preAssignedId
     */
    protected void fillTransactionId(String preAssignedId) {
        if (preAssignedId != null && !preAssignedId.isEmpty()) {
            this.id = preAssignedId;
        } else {
            this.id = IdGenerator.generateTransactionId();
        }

        if (this.id.startsWith("t_")) {
            this.id = "t_" + this.id;
        }
    }

    /**
     * 执行转账操作，将钱从买家的钱包转到卖家的钱包中。真正的转账操作是通过调用 WalletRpcService RPC 服务
     * 来完成的。除此之外，代码中还涉及一个分布式锁 DistributedLock 单例类，用来避免 Transaction 并发执行
     * ，导致用户的钱被重复转出。
     *
     * @return
     * @throws InvalidTransactionException
     */
    public boolean execute() throws InvalidTransactionException {

        if (buyerId == null || (sellerId == null || amount < 0.0)) {
            throw new InvalidTransactionException("");
        }

        if (status == STATUS.EXECUTED) {
            return true;
        }
        boolean isLocked = false;
        try {
            //优化点2
            //isLocked = RedisDistributedLock.getSingletonIntance().lockTransction(id);
            isLocked = transactionLock.lock(id);
            if (!isLocked) {
                return false; //锁定未成功，返回false，job兜底执行
            }

            if (status == STATUS.EXECUTED) return true; // double check

            //优化点3
            //long executionInvokedTimestamp = System.currentTimeMillis();
            //if (executionInvokedTimestamp - createTimestamp > FOURTEEN_DAYS) {
            if(isExpired()){
                this.status = STATUS.EXPIRED;
                return false;
            }

            //优化点1：WalletRpcService walletRpcService = new WalletRpcService();
            String walletTransactionId = walletRpcService.moveMoney(id, buyerId, sellerId, amount);
            if (walletTransactionId != null) {
                this.status = STATUS.EXECUTED;
                return true;
            } else {
                this.status = STATUS.FAILED;
                return false;
            }

        } finally {
            if (isLocked) {
                //优化点2
                //RedisDistributedLock.getSingletonIntance().unlockTransction(id);
                transactionLock.unlock(id);
            }
        }
    }

    //优化点1：注入外部依赖
    public void setWalletRpcService(WalletRpcService walletRpcService) {
        this.walletRpcService = walletRpcService;
    }

    //优化点2：注入新加锁对象
    public void setTransactionLock(TransactionLock transactionLock) {
        this.transactionLock = transactionLock;
    }

    //优化点3: 未决行为处理方法 -- 封装
    protected boolean isExpired(){

        long executionInvokedTimestamp = System.currentTimeMillis();
        return executionInvokedTimestamp - createTimestamp > FOURTEEN_DAYS;
    }
}
