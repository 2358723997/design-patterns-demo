package com.design.patterns.demo.orientedobject.virtualwallect.service.impl.congestive;

import java.math.BigDecimal;

import com.design.patterns.demo.orientedobject.virtualwallect.enums.TransactionType;
import com.design.patterns.demo.orientedobject.virtualwallect.exception.InsufficientBalanceException;
import com.design.patterns.demo.orientedobject.virtualwallect.exception.InvalidAmountException;
import com.design.patterns.demo.orientedobject.virtualwallect.exception.NoSufficientBalanceException;
import com.design.patterns.demo.orientedobject.virtualwallect.repository.VirtualWalletRepository;
import com.design.patterns.demo.orientedobject.virtualwallect.repository.VirtualWalletTransactionRepository;
import com.design.patterns.demo.orientedobject.virtualwallect.repository.entity.VirtualWalletEntity;
import com.design.patterns.demo.orientedobject.virtualwallect.repository.entity.VirtualWalletTransactionEntity;
import com.design.patterns.demo.orientedobject.virtualwallect.service.VirtualWallectService;
import com.design.patterns.demo.orientedobject.virtualwallect.service.impl.congestive.domain.VirtualWallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private VirtualWalletRepository walletRepo;

    @Autowired
    private VirtualWalletTransactionRepository transactionRepo;

    @Override
    public BigDecimal getBalance(Long wallectId) {
        return walletRepo.getBanlance(wallectId);
    }

    @Transactional
    @Override
    public void debit(Long wallectId, BigDecimal amount) throws NoSufficientBalanceException, InsufficientBalanceException {
        VirtualWalletEntity entity = walletRepo.getWallectEntity(wallectId);
        VirtualWallet wallet = covert(entity);
        wallet.debit(amount);
        VirtualWalletTransactionEntity transactionEntity = new VirtualWalletTransactionEntity();
        transactionEntity.setAmount(amount);
        transactionEntity.setCreateTime(System.currentTimeMillis());
        transactionEntity.setType(TransactionType.DEBIT);
        transactionEntity.setFromWalletId(wallectId);

        transactionRepo.saveTransaction(transactionEntity);
        walletRepo.updateBalance(wallectId, wallet.balance());

    }

    @Transactional
    @Override
    public void credit(Long wallectId, BigDecimal amount) throws  InvalidAmountException {
        VirtualWalletEntity entity = walletRepo.getWallectEntity(wallectId);
        VirtualWallet wallet = covert(entity);
        wallet.cerbit(amount);

        VirtualWalletTransactionEntity transactionEntity = new VirtualWalletTransactionEntity();
        transactionEntity.setAmount(amount);
        transactionEntity.setCreateTime(System.currentTimeMillis());
        transactionEntity.setType(TransactionType.CREDIT);
        transactionEntity.setFromWalletId(wallectId);

        transactionRepo.saveTransaction(transactionEntity);
        walletRepo.updateBalance(wallectId, wallet.balance());
    }

    @Transactional
    @Override
    public void tranfer(Long fromWallectId, Long toWallectId, BigDecimal amount) throws NoSufficientBalanceException, InsufficientBalanceException, InvalidAmountException {
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

    private VirtualWallet covert(VirtualWalletEntity entity) {
        VirtualWallet result = new VirtualWallet();
        result.setBalance(entity.getBalance());
        result.setCreateTime(entity.getCreateTime());
        return result;
    }
}
