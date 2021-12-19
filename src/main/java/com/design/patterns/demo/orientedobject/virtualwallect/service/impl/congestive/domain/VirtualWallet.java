package com.design.patterns.demo.orientedobject.virtualwallect.service.impl.congestive.domain;

import java.math.BigDecimal;

import com.design.patterns.demo.orientedobject.virtualwallect.exception.InsufficientBalanceException;
import com.design.patterns.demo.orientedobject.virtualwallect.exception.InvalidAmountException;
import com.design.patterns.demo.orientedobject.virtualwallect.exception.NoSufficientBalanceException;
import lombok.Getter;
import lombok.Setter;

/**
 * VirtualWallet类
 *
 * Domain领域模型(充血模型)
 *
 * @author wangjixue
 * @date 12/18/21 8:46 PM
 */
public class VirtualWallet {

    //虚拟钱包账号
    private Long id;

    private Long createTime = System.currentTimeMillis();
    //余额
    private BigDecimal balance = BigDecimal.ZERO;
    //是允许透支
    private boolean isAllowedOverdraft = true;
    //透支额度
    private BigDecimal overdraftAmount = BigDecimal.ZERO;
    //冻结额度
    private BigDecimal frozenAmount = BigDecimal.ZERO;

    public VirtualWallet(Long preAllocatedId) {
        this.id = preAllocatedId;
    }

    public VirtualWallet(Long id, Long createTime, BigDecimal balance) {
        this.id = id;
        this.createTime = createTime;
        this.balance = balance;
    }

    public BigDecimal balance() {
        return balance;
    }

    /**
     * 出账
     *
     * @param amount
     * @throws NoSufficientBalanceException
     */
    public void debit(BigDecimal amount) throws InsufficientBalanceException {
        BigDecimal totalAvaliableBalance = getAvaliableBalance();
        if (totalAvaliableBalance.compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Failed to debit, the reason is the virtual wallet balance is less than amount.");
        }
        this.balance = balance.subtract(amount);
    }

    /**
     * 入账
     *
     * @param amount
     * @throws NoSufficientBalanceException
     */
    public void cerbit(BigDecimal amount) throws InvalidAmountException {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidAmountException("Failed to cerbit, the reason is the amount is less than zero.");
        }
        this.balance = balance.add(amount);
    }

    /**
     * 冻结部分余额
     *
     * @param amount
     */
    public void freeze(BigDecimal amount) {

    }

    /**
     * 解冻部分余额
     *
     * @param amount
     */
    public void unfreeze(BigDecimal amount) {

    }

    /**
     * 增加透支额度
     *
     * @param amount
     */
    public void increaseOverdraftAmount(BigDecimal amount) {

    }

    /**
     * 减少透支额度
     *
     * @param amount
     */
    public void decreaseOverdraftAmount(BigDecimal amount) {

    }

    /**
     * 开启透支
     *
     */
    public void openOverdraft() {

    }

    /**
     * 关闭透支
     *
     */
    public void closeOverdraft() {

    }

    public BigDecimal getAvaliableBalance() {
        BigDecimal totalAvaliableBalance = this.balance.subtract(this.frozenAmount);
        if (isAllowedOverdraft) {
            totalAvaliableBalance.add(overdraftAmount);
        }
        return totalAvaliableBalance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
