package com.design.patterns.demo.designpattern.action.template.reuse;

import java.io.IOException;

/**
 * MyInputStream类
 * <p>
 * 模板模式有两大作用：复用和扩展
 * <p>
 * 第一个作用：复用
 * <p>
 * 模板模式把一个算法中不变的流程抽象到父类的模板方法 templateMethod() 中，
 * 将可变的部分 method1()、method2() 留给子类 ContreteClass1 和
 * ContreteClass2 来实现。所有的子类都可以复用父类中模板方法定义的流程代码。
 * <p>
 * <p>
 * 在代码InputStream中，read() 函数是一个模板方法，定义了读取数据的整个流程，
 * 并且暴露了一个可以由子类来定制的抽象方法。不过这个方法也被命名为了 read()，
 * 只是参数跟模板方法不同。
 *
 * @author wangjixue
 * @date 2022/9/3 2:23 PM
 */
public abstract class MyInputStream {


    public int read(byte b[], int off, int len) throws IOException {
        if (b == null) {
            throw new NullPointerException();
        } else if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return 0;
        }

        int c = read();

        if (c == -1) {
            return -1;
        }

        b[off] = (byte) c;
        int i = 1;
        try {
            for (; i < len; i++) {
                c = read();
                if (c == -1) {
                    break;
                }
                b[off + i] = (byte) c;
            }
        } catch (IOException e) {

        }
        return i;
    }

    public abstract int read()throws IOException;
}
