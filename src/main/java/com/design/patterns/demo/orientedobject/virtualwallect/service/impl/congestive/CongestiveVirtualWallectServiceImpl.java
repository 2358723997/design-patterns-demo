package com.design.patterns.demo.orientedobject.virtualwallect.service.impl.congestive;

import java.math.BigDecimal;

import com.design.patterns.demo.orientedobject.virtualwallect.repository.VirtualWalletRepository;
import com.design.patterns.demo.orientedobject.virtualwallect.repository.VirtualWalletTransactionRepository;
import com.design.patterns.demo.orientedobject.virtualwallect.service.VirtualWallectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CongestiveVirtualWallectServiceImpl类
 *
 * 基于充血模型的设计：方法和数据在一起
 *
 * @author wangjixue
 * @date 12/18/21 8:33 PM
 */

@Service("CongestiveVirtualWallectServiceImpl")
public class CongestiveVirtualWallectServiceImpl implements VirtualWallectService {

    @Autowired
    private VirtualWalletRepository walletRepository;

    @Autowired
    private VirtualWalletTransactionRepository transactionRepository;

    @Override
    public BigDecimal getBalance(Long wallectId) {
        return null;
    }

    @Override
    public void debit(Long wallectId, BigDecimal amount) {

    }

    @Override
    public void credit(Long wallectId, BigDecimal amount) {

    }

    @Override
    public void tranfer(Long fromWallectId, Long toWallectId, BigDecimal amount) {

    }
}
