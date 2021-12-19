package com.design.patterns.demo.orientedobject.virtualwallect.repository;

import com.design.patterns.demo.orientedobject.virtualwallect.repository.entity.VirtualWalletTransactionEntity;

/**
 * VirtualWalletTransactionRepository接口
 *
 * @author wangjixue
 * @date 12/18/21 8:36 PM
 */
public interface VirtualWalletTransactionRepository {

    void saveTransaction(VirtualWalletTransactionEntity transactionEntity);
}
