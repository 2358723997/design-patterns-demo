package com.design.patterns.demo.coderestructure.unittest.noframework;

/**
 * TestRunner类
 *
 * 测试启动类
 *
 * @author wangjixue
 * @date 4/17/22 6:09 PM
 */
public class TestCaseRunner {

    public static void main(String[] args) {
        System.out.println("Run testToNumber()");
        new TextTest().testToNumber();

        System.out.println("Run testToNumber_nullorEmpty()");
        new TextTest().testToNumber_nullorEmpty();

        System.out.println("Run testToNumber_containsLeadingAndTrailingSpaces()");
        new TextTest().testToNumber_containsLeadingAndTrailingSpaces();

        System.out.println("Run testToNumber_containsMultiLeadingAndTrailingSpaces()");
        new TextTest().testToNumber_containsMultiLeadingAndTrailingSpaces();

        System.out.println("Run testToNumber_containsInvalidCharaters()");
        new TextTest().testToNumber_containsInvalidCharaters();
    }
}
