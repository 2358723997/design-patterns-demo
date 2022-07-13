package com.design.patterns.demo.designpattern.structure.decorator;

import java.io.IOException;

/**
 * MyInputStreamDemo类
 *
 * 从 Java IO 的设计来看，装饰器模式相对于简单的组合关系，还有两个比较特殊的地方。
 *
 * 第一个比较特殊的地方是：装饰器类和原始类继承同样的父类，这样我们可以对原始类“嵌套”
 * 多个装饰器类。
 *
 * 比如，下面这样一段代码，我们对MyFileInputStream嵌套了两个装饰器类：MyBufferedInputStream和MyDataInputStream，
 * 让它既支持缓存读取，又支持按照基本数据类型来读取数据。
 **
 * @author wangjixue
 * @date 7/13/22 11:23 PM
 */
public class MyInputStreamDemo {
    public static void main(String[] args) throws IOException {
        MyInputStream in = new MyFileInputStream("/user/file.txt");
        MyInputStream bin = new MyBufferedInputStream(in);
        MyDataInputStream din = new MyDataInputStream(bin);
        int data = din.readInt();
    }
}
