package com.design.patterns.demo.coderestructure.codedecouples.userstory;

import javax.transaction.InvalidTransactionException;

import com.design.patterns.demo.coderestructure.codedecouples.STATUS;
import com.design.patterns.demo.coderestructure.codedecouples.v0.Transaction;
import com.design.patterns.demo.coderestructure.codedecouples.v1.MockWalletRpcServiceOne;
import com.design.patterns.demo.coderestructure.codedecouples.v1.TransactionLock;
import com.design.patterns.demo.coderestructure.codedecouples.v1.TransactionNew;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * TransactionTest类
 *
 * 在 Transaction 类中，主要逻辑集中在 execute() 函数中，所以它是我们测试的重点对象。
 * 为了尽可能全面覆盖各种正常和异常情况，针对这个函数，我设计了下面 6 个测试用例。
 *
 * 1.正常情况下，交易执行成功，回填用于对账（交易与钱包的交易流水）用的 walletTransactionId，
 * 交易状态设置为 EXECUTED，函数返回 true。
 *
 * 2.buyerId、sellerId 为 null、amount 小于 0，返回 InvalidTransactionException。
 *
 * 3.交易已过期（createTimestamp 超过 14 天），交易状态设置为 EXPIRED，返回 false。
 *
 * 4.交易已经执行了（status==EXECUTED），不再重复执行转钱逻辑，返回 true。
 *
 * 5.钱包（WalletRpcService）转钱失败，交易状态设置为 FAILED，
 *
 * 6. 函数返回 false。交易正在执行着，不会被重复执行，函数直接返回 false。
 */
public class TransactionTest {

    /**
     * 1. 正常情况下，交易执行成功，回填用于对账（交易与钱包的交易流水）用的 walletTransactionId，
     * 交易状态设置为 EXECUTED，函数返回 true。
     *
     * 分析：execute() 函数的执行依赖两个外部的服务，一个是 RedisDistributedLock，
     * 一个 WalletRpcService。这就导致单元测试不能按照我们的预期数据进行返回，这时候就需要被测代码与
     * 外部系统解依赖，这种解依赖的方法就叫作“mock”
     *
     * 所谓的 mock 就是用一个“假”的服务替换真正的服务。mock 的服务完全在我们的控制之下，
     * 模拟输出我们想要的数据。
     *
     * 针对WalletRpcService 我们通过用 MockWalletRpcServiceOne、MockWalletRpcServiceTwo
     * 来替换代码中的真正的 WalletRpcService，
     *
     * 具体改动为：应用依赖注入，将 WalletRpcService 对象的创建反转给上层逻辑，在外部创建好之后，
     * 再注入到 Transaction 类中
     *
     * 针对RedisDistributedLock，因为RedisDistributedLock 是一个单例类。单例相当于一个全局变量，
     * 我们无法 mock（无法继承和重写方法），也无法通过依赖注入的方式来替换。
     *
     * 场景1：如果 RedisDistributedLock 是我们自己维护的，可以自由修改、重构，那我们可以将其改为非单例的模式，
     * 或者定义一个接口，比如 IDistributedLock，让 RedisDistributedLock 实现这个接口。这样我们就可
     * 以像前面 WalletRpcService 的替换方式那样，替换 RedisDistributedLock 为
     * MockRedisDistributedLock 了。
     *
     * 场景二：如果 RedisDistributedLock 不是我们维护的，我们无权去修改这部分代码，这时候我们可以针对上锁这部分
     * 逻辑重新封装，我们创建一个TransactionLock来封装加锁和释放锁逻辑，然后在Transaction依赖注册TransactionLock
     * 来解决。
     */
    @Test
    public void testExecute() throws InvalidTransactionException {
        Long buyerId = 123L;
        Long sellerId = 234L;
        Long productId = 345L;
        String orderId = "456";

        Transaction transaction = new Transaction(null, buyerId, sellerId,
            productId, orderId);
        boolean executedResult = transaction.execute();
        assertTrue(executedResult);
    }

    @Test
    public void testExecute_new() throws InvalidTransactionException {
        Long buyerId = 123L;
        Long sellerId = 234L;
        Long productId = 345L;
        String orderId = "456";

        TransactionLock mockLock = new TransactionLock() {
            public boolean lock(String id) { return true; }

            public void unlock() {}
        };

        TransactionNew transaction = new TransactionNew(null, buyerId, sellerId,
            productId, orderId);
        // 使用mock对象来替代真正的RPC服务
        transaction.setWalletRpcService(new MockWalletRpcServiceOne());
        //使用mock对象来替代RedisDistributedLock
        transaction.setTransactionLock(mockLock);
        boolean executedResult = transaction.execute();
        assertTrue(executedResult);
        assertEquals(STATUS.EXECUTED, transaction.getStatus());
    }

    /**
     * 交易已过期（createTimestamp 超过 14 天），交易状态设置为 EXPIRED，返回 false。
     *
     * 分析：直接使用setCreatedTimestamp()方法是否合理？
     *
     * 答案：违反了封装特性，不合理。在 Transaction 类的设计中，createTimestamp
     * 是在交易生成时（也就是构造函数中）自动获取的系统时间，本来就不应该人为地轻易修改，所以，
     * 暴露 createTimestamp 的 set 方法，虽然带来了灵活性，但也带来了不可控性。因为，
     * 我们无法控制使用者是否会调用 set 方法重设 createTimestamp，而重设 createTimestamp
     * 并非我们的预期行为。
     *
     * 如何优化：代码中包含跟“时间”有关的“未决行为”逻辑。我们一般的处理方式是将这种未决行为逻辑重新封装。
     * 针对 Transaction 类，我们只需要将交易是否过期的逻辑，封装到 isExpired() 函数中即可
     */
    @Test
    public void testExecute_with_TransactionIsExpired() throws InvalidTransactionException {
        Long buyerId = 123L;
        Long sellerId = 234L;
        Long productId = 345L;
        String orderId = "456";
        Transaction transaction = new Transaction(null, buyerId, sellerId, productId, orderId);
        //创建时间设置为14天前
        transaction.setCreatedTimestamp(System.currentTimeMillis() - 14*24*3600*1000l);
        boolean actualResult = transaction.execute();
        assertFalse(actualResult);
        assertEquals(STATUS.EXPIRED, transaction.getStatus());
    }


    @Test
    public void testExecuteNew_with_TransactionIsExpired() throws InvalidTransactionException {
        Long buyerId = 123L;
        Long sellerId = 234L;
        Long productId = 345L;
        String orderId = "456";
        TransactionNew transaction = new TransactionNew(null, buyerId, sellerId, productId, orderId) {
            protected boolean isExpired() {
                return true;
            }
        };
        boolean actualResult = transaction.execute();
        assertFalse(actualResult);
        assertEquals(STATUS.EXPIRED, transaction.getStatus());
    }
}
