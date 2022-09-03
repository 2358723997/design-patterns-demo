package com.design.patterns.demo.designpattern.action.template.reuse;

import java.io.IOException;

/**
 * MyByteArrayInputStream类
 *
 * @author wangjixue
 * @date 2022/9/3 2:32 PM
 */
public class MyByteArrayInputStream extends MyInputStream {

    private int pos;
    private int count;
    private int[] buf;

    @Override
    public int read() throws IOException {

        return (pos < count) ? (buf[pos++] & 0xff):-1;
    }
}
