package com.design.patterns.demo.designpattern.create.factory.di;

/**
 * MyApplicationContext类
 *
 * 执行入口
 *
 * @author wangjixue
 * @date 6/28/22 11:31 PM
 */
public interface MyApplicationContext {

  Object getBean(String beanId);
}
