package com.design.patterns.demo.orientedobject.virtualwallect.repository.entity;

import java.math.BigDecimal;

import com.design.patterns.demo.orientedobject.virtualwallect.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;

/**
 * VirtualWalletTransactionEntity类
 *
 * @author wangjixue
 * @date 12/19/21 12:13 PM
 */
@Getter
@Setter
public class VirtualWalletTransactionEntity {

    private BigDecimal amount;

    private Long createTime;

    private TransactionType type;

    private Long fromWalletId;

    private Long toWalletId;
}
