package com.design.patterns.demo.coderestructure.unittest.noframework;

/**
 * Assert类
 *
 * @author wangjixue
 * @date 4/17/22 5:50 PM
 */
public class Assert {
    public static void assertEquals(Integer expectedValue, Integer actualValue) {
        if (actualValue != expectedValue) {
            String message = String.format("Test failed, expected: %d, actual: %d.", expectedValue, actualValue);
            System.err.println(message);
        } else {
            System.err.println("Test successed.");
        }

    }

    public static void assertNull(Integer actualValue) {
        boolean isNull = actualValue == null;
        if(isNull){
            System.out.println("Test successed.");
        }else {
            String message = String.format("Test failed, the value is not null: %d.", actualValue);
            System.out.println(message);
        }
    }
}
