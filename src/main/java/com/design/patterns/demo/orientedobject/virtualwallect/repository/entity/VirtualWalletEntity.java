package com.design.patterns.demo.orientedobject.virtualwallect.repository.entity;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * VirtualWalletEntity类
 *
 * @author wangjixue
 * @date 12/19/21 12:05 PM
 */
@Getter
@Setter
public class VirtualWalletEntity {
    private Long id;

    private Long createTime;

    private BigDecimal balance;
}
