package com.design.patterns.demo.orientedobject.virtualwallect.service.impl.anemia;

import java.math.BigDecimal;

import com.design.patterns.demo.orientedobject.virtualwallect.enums.TransactionType;
import com.design.patterns.demo.orientedobject.virtualwallect.exception.NoSufficientBalanceException;
import com.design.patterns.demo.orientedobject.virtualwallect.repository.VirtualWalletRepository;
import com.design.patterns.demo.orientedobject.virtualwallect.repository.VirtualWalletTransactionRepository;
import com.design.patterns.demo.orientedobject.virtualwallect.repository.entity.VirtualWalletEntity;
import com.design.patterns.demo.orientedobject.virtualwallect.repository.entity.VirtualWalletTransactionEntity;
import com.design.patterns.demo.orientedobject.virtualwallect.service.VirtualWallectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private VirtualWalletRepository walletRepo;

    @Autowired
    private VirtualWalletTransactionRepository transactionRepo;

    @Override
    public BigDecimal getBalance(Long wallectId) {
        return walletRepo.getBanlance(wallectId);
    }

    @Transactional
    @Override
    public void debit(Long wallectId, BigDecimal amount) throws NoSufficientBalanceException {
        VirtualWalletEntity entity = walletRepo.getWallectEntity(wallectId);
        BigDecimal balance = entity.getBalance();
        if (balance.compareTo(amount) < 0) {
            throw new NoSufficientBalanceException("Failed to debit, the reason is the virtual wallet balance is less than amount.");
        }
        VirtualWalletTransactionEntity transactionEntity = new VirtualWalletTransactionEntity();
        transactionEntity.setAmount(amount);
        transactionEntity.setCreateTime(System.currentTimeMillis());
        transactionEntity.setType(TransactionType.DEBIT);
        transactionEntity.setFromWalletId(wallectId);
        transactionRepo.saveTransaction(transactionEntity);
        walletRepo.updateBalance(wallectId, balance.subtract(amount));

    }

    @Transactional
    @Override
    public void credit(Long wallectId, BigDecimal amount) {
        VirtualWalletTransactionEntity transactionEntity = new VirtualWalletTransactionEntity();
        transactionEntity.setAmount(amount);
        transactionEntity.setCreateTime(System.currentTimeMillis());
        transactionEntity.setType(TransactionType.CREDIT);
        transactionEntity.setFromWalletId(wallectId);
        transactionRepo.saveTransaction(transactionEntity);
        VirtualWalletEntity entity = walletRepo.getWallectEntity(wallectId);
        BigDecimal balance = entity.getBalance();
        walletRepo.updateBalance(wallectId, balance.add(amount));
    }

    @Transactional
    @Override
    public void tranfer(Long fromWallectId, Long toWallectId, BigDecimal amount) throws NoSufficientBalanceException {
        VirtualWalletTransactionEntity transactionEntity = new VirtualWalletTransactionEntity();
        transactionEntity.setAmount(amount);
        transactionEntity.setCreateTime(System.currentTimeMillis());
        transactionEntity.setType(TransactionType.TRANSFER);
        transactionEntity.setFromWalletId(fromWallectId);
        transactionEntity.setToWalletId(toWallectId);
        transactionRepo.saveTransaction(transactionEntity);
        debit(fromWallectId,amount);
        credit(toWallectId,amount);
    }
}
