package com.design.patterns.demo.coderestructure.codedecouples.v1;

import com.design.patterns.demo.coderestructure.codedecouples.WalletRpcService;

/**
 * MockWalletRpcServiceOne类
 *
 * @author wangjixue
 * @date 4/17/22 11:25 PM
 */
public class MockWalletRpcServiceOne extends WalletRpcService {

    public String moveMoney(String id, Long buyerId, Long sellerId, Double amount) {
        return "123bac";
    }
}
