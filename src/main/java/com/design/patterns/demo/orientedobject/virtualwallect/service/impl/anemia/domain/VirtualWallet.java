package com.design.patterns.demo.orientedobject.virtualwallect.service.impl.anemia.domain;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * VirtualWallet类
 *
 * @author wangjixue
 * @date 12/18/21 8:46 PM
 */
@Getter
@Setter
public class VirtualWallet {
    private Long id;

    private Long createTime;

    private BigDecimal balance;
}
