package com.design.patterns.demo.orientedobject.virtualwallect.repository;

import java.math.BigDecimal;

import com.design.patterns.demo.orientedobject.virtualwallect.repository.entity.VirtualWalletEntity;

/**
 * VirtualWalletRepository类
 *
 * @author wangjixue
 * @date 12/18/21 8:36 PM
 */
public interface VirtualWalletRepository {
    
    BigDecimal getBanlance(Long wallectId);

    VirtualWalletEntity getWallectEntity(Long wallectId);

    void updateBalance(Long wallectId, BigDecimal subtract);
}
