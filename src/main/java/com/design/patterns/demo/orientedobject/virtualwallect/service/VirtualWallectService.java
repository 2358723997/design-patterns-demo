package com.design.patterns.demo.orientedobject.virtualwallect.service;

import java.math.BigDecimal;

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
     * @param account
     */
    void debit(Long wallectId, BigDecimal account);

    /**
     * 入账
     *
     * @param wallectId
     * @param account
     */
    void credit(Long wallectId, BigDecimal account);

    /**
     * 转账
     *
     * @param fromWallectId
     * @param toWallectId
     * @param account
     */
    void tranfer(Long fromWallectId, Long toWallectId, BigDecimal account);
}
