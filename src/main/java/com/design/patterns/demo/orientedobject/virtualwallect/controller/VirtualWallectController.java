package com.design.patterns.demo.orientedobject.virtualwallect.controller;

import java.math.BigDecimal;

import com.design.patterns.demo.orientedobject.virtualwallect.exception.InsufficientBalanceException;
import com.design.patterns.demo.orientedobject.virtualwallect.exception.InvalidAmountException;
import com.design.patterns.demo.orientedobject.virtualwallect.exception.NoSufficientBalanceException;
import com.design.patterns.demo.orientedobject.virtualwallect.service.VirtualWallectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * VirtualWallectController类
 *
 * @author wangjixue
 * @date 12/18/21 8:10 PM
 */
@RestController("/VirtualWallect")
public class VirtualWallectController {
    @Autowired
    private VirtualWallectService service;

    /**
     * 查询余额
     *
     * @param wallectId
     * @return
     */

    @RequestMapping(value = "/GetBalance", method = {RequestMethod.GET, RequestMethod.POST})
    public BigDecimal getBalance(Long wallectId) {
        return service.getBalance(wallectId);
    }

    /**
     * 出账
     *
     * @param wallectId
     * @param amount
     */

    @PostMapping(value = "/Debit")
    public void debit(Long wallectId, BigDecimal amount) throws NoSufficientBalanceException, InsufficientBalanceException {
        service.debit(wallectId, amount);
    }

    /**
     * 入账
     *
     * @param wallectId
     * @param amount
     */

    @PostMapping(value = "/Crebit")
    public void credit(Long wallectId, BigDecimal amount) throws InvalidAmountException {
        service.credit(wallectId, amount);
    }

    /**
     * 转账
     *
     * @param fromWallectId
     * @param toWallectId
     * @param amount
     */
    @PostMapping(value = "/Tranfer")
    public void tranfer(Long fromWallectId, Long toWallectId, BigDecimal amount) throws NoSufficientBalanceException, InsufficientBalanceException, InvalidAmountException {
        service.tranfer(fromWallectId, toWallectId, amount);
    }

}
