package com.design.patterns.demo.coderestructure.unittest.noframework;

import com.design.patterns.demo.coderestructure.unittest.Text;

/**
 * TextTest类
 *
 * 如果我们要测试 Text 类中的 toNumber() 函数的正确性，应该如何编写单元测试呢？
 *
 * 单元测试更多的是考验程序员思维的缜密程度，看能否设计出覆盖各种正常及异常情况的测试用例
 * (围绕入参，出参，异常进行编写)，来保证代码在任何预期或非预期的情况下都能正确运行。
 *
 * 为了保证测试的全面性，针对 toNumber() 函数，我们需要设计下面这样几个测试用例。
 *
 * @author wangjixue
 * @date 4/17/22 5:40 PM
 */
public class TextTest {

    /**
     * 如果字符串只包含数字：“123”，toNumber() 函数输出对应的整数：123。
     *
     */
    public void testToNumber() {
        Text text = new Text("123");
        Assert.assertEquals(123, text.toNumber());
    }

    /**
     * 如果字符串是空或者 null，toNumber() 函数返回：null。
     *
     */
    public void testToNumber_nullorEmpty() {
        Text text1 = new Text(null);
        Assert.assertNull(text1.toNumber());

        Text text2 = new Text("");
        Assert.assertNull(text1.toNumber());
    }

    /**
     * 如果字符串包含首尾空格：“ 123”，“123 ”，“ 123 ”，toNumber() 返回对应的整数：123。
     *
     */
    public void testToNumber_containsLeadingAndTrailingSpaces(){
        Text text1 = new Text(" 123");
        Assert.assertEquals(123, text1.toNumber());

        Text text2 = new Text("123 ");
        Assert.assertEquals(123, text2.toNumber());

        Text text3 = new Text(" 123 ");
        Assert.assertEquals(123, text3.toNumber());
    }

    /**
     * 如果字符串包含多个首尾空格：“ 123 ”，toNumber() 返回对应的整数：123；
     *
     */
    public void testToNumber_containsMultiLeadingAndTrailingSpaces(){
        Text text1 = new Text("  123");
        Assert.assertEquals(123, text1.toNumber());

        Text text2 = new Text("123  ");
        Assert.assertEquals(123, text2.toNumber());

        Text text3 = new Text("  123  ");
        Assert.assertEquals(123, text3.toNumber());
    }

    /**
     * 如果字符串包含非数字字符：“123a4”，“123 4”，toNumber() 返回 null；
     *
     */
    public void testToNumber_containsInvalidCharaters(){
        Text text1 = new Text("123a4");
        Assert.assertNull(text1.toNumber());

        Text text2 = new Text("123 4");
        Assert.assertNull(text2.toNumber());
    }
}
