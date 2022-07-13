package com.design.patterns.demo.designpattern.structure.decorator;

/**
 * MyFileInputStream类
 *
 * @author wangjixue
 * @date 7/13/22 11:24 PM
 */
public class MyFileInputStream extends MyInputStream {

    private MyInputStream in;

    private String filePath;

    public MyFileInputStream(String filePath) {
        new MyFileInputStream(this,filePath);
    }

    public MyFileInputStream(MyInputStream in, String filePath) {
        this.in = in;
        this.filePath = filePath;
    }
}
