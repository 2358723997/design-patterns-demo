package com.design.patterns.demo.coderestructure.codedecouples.utils;

import java.util.UUID;

/**
 * IdGenerator类
 *
 * @author wangjixue
 * @date 4/17/22 9:55 PM
 */
public class IdGenerator {

    public static String generateTransactionId() {
       return UUID.randomUUID().toString();
    }
}
