package com.design.patterns.demo.designpattern.action.template.code;

/**
 * TemplateDemo类
 *
 * @author wangjixue
 * @date 2022/9/3 2:10 PM
 */
public class TemplateDemo {
    public static void main(String[] args) {
        AbstractClass demo = new ConCreateClass1();
        demo.templateMethod();
    }
}
