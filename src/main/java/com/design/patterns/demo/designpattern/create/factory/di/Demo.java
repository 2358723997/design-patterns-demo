package com.design.patterns.demo.designpattern.create.factory.di;

/**
 * Demo类
 *
 * @author wangjixue
 * @date 6/28/22 11:30 PM
 */
public class Demo {
    public static void main(String[] args) {
        MyApplicationContext context = new MyClassPathXmlApplicationContext("mybeans.xml");
    }
}
