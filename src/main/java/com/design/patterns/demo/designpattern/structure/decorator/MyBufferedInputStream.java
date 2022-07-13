package com.design.patterns.demo.designpattern.structure.decorator;

import java.io.IOException;

/**
 * MyBufferedInputStream类
 *
 * @author wangjixue
 * @date 7/13/22 11:19 PM
 */
public class MyBufferedInputStream extends MyInputStream{
    private volatile MyInputStream in;

    public MyBufferedInputStream(MyInputStream in) {
        this.in = in;
    }

    //TODO 重新read等相关方法，实现基于缓存的读数据接口
    @Override
    public int read(byte b[]) throws IOException {
        return in.read(b, 0, b.length);
    }

    @Override
    public int read(byte[] b, int offset, int length) throws IOException {
        //TODO 真实处理逻辑
        return in.read(b,offset,length);
    }
}
