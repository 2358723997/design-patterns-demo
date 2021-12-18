package com.design.patterns.demo.orientedobject.virtualwallect.service.impl.anemia;

import java.math.BigDecimal;

import com.design.patterns.demo.orientedobject.virtualwallect.repository.VirtualWalletRepository;
import com.design.patterns.demo.orientedobject.virtualwallect.repository.VirtualWalletTransactionRepository;
import com.design.patterns.demo.orientedobject.virtualwallect.service.VirtualWallectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AnemiaVirtualWallectServiceImpl类
 *
 * 基于贫血模型的设计：方法和数据分离
 *
 * @author wangjixue
 * @date 12/18/21 8:31 PM
 */

@Service("AnemiaVirtualWallectServiceImpl")
public class AnemiaVirtualWallectServiceImpl implements VirtualWallectService {

    @Autowired
    private VirtualWalletRepository walletRepository;

    @Autowired
    private VirtualWalletTransactionRepository transactionRepository;


    @Override
    public BigDecimal getBalance(Long wallectId) {
        return null;
    }

    @Override
    public void debit(Long wallectId, BigDecimal account) {

    }

    @Override
    public void credit(Long wallectId, BigDecimal account) {

    }

    @Override
    public void tranfer(Long fromWallectId, Long toWallectId, BigDecimal account) {

    }
}
