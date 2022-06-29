package com.design.patterns.demo.designpattern.create.factory.di;

import com.design.patterns.demo.designpattern.create.factory.di.domain.RateLimiter;

/**
 * Demo类
 *
 * @author wangjixue
 * @date 6/28/22 11:30 PM
 */
public class Demo {
    public static void main(String[] args) {
        MyApplicationContext applicationContext = new MyClassPathXmlApplicationContext("mybeans.xml");

        RateLimiter rateLimiter = (RateLimiter) applicationContext.getBean("rateLimiter");
        rateLimiter.test();
    }
}
