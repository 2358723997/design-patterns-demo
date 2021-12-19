package com.design.patterns.demo.orientedobject.virtualwallect.service;

import java.math.BigDecimal;

import com.design.patterns.demo.orientedobject.virtualwallect.exception.InsufficientBalanceException;
import com.design.patterns.demo.orientedobject.virtualwallect.exception.InvalidAmountException;
import com.design.patterns.demo.orientedobject.virtualwallect.exception.NoSufficientBalanceException;

/**
 * VirtualWallectService类
 *
 * @author wangjixue
 * @date 12/18/21 8:12 PM
 */
public interface VirtualWallectService {
    /**
     * 查询余额
     *
     * @param wallectId
     * @return
     */
    BigDecimal getBalance(Long wallectId);

    /**
     * 出账
     *
     * @param wallectId
     * @param amount
     */
    void debit(Long wallectId, BigDecimal amount) throws NoSufficientBalanceException, InsufficientBalanceException;

    /**
     * 入账
     *
     * @param wallectId
     * @param amount
     */
    void credit(Long wallectId, BigDecimal amount) throws InvalidAmountException;

    /**
     * 转账
     *
     * @param fromWallectId
     * @param toWallectId
     * @param amount
     */
    void tranfer(Long fromWallectId, Long toWallectId, BigDecimal amount) throws NoSufficientBalanceException, InsufficientBalanceException, InvalidAmountException;
}
