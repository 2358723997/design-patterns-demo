package com.design.patterns.demo.designpattern.structure.decorator;

import java.io.DataInput;
import java.io.IOException;

/**
 * MyDataInputStream类
 *
 * @author wangjixue
 * @date 7/13/22 11:22 PM
 */
public class MyDataInputStream extends MyInputStream implements DataInput {
    private volatile MyInputStream in;

    public MyDataInputStream(MyInputStream in) {
        this.in = in;
    }

    //TODO 重新read等相关方法，实现基于类型的读数据接口
    @Override
    public int read(byte b[]) throws IOException {
        return in.read(b, 0, b.length);
    }

    @Override
    public int read(byte[] b, int offset, int length) throws IOException {
        //TODO 真实处理逻辑
        return in.read(b,offset,length);
    }

    @Override
    public void readFully(byte[] b) throws IOException {

    }

    @Override
    public void readFully(byte[] b, int off, int len) throws IOException {

    }

    @Override
    public int skipBytes(int n) throws IOException {
        return 0;
    }

    @Override
    public boolean readBoolean() throws IOException {
        return false;
    }

    @Override
    public byte readByte() throws IOException {
        return 0;
    }

    @Override
    public int readUnsignedByte() throws IOException {
        return 0;
    }

    @Override
    public short readShort() throws IOException {
        return 0;
    }

    @Override
    public int readUnsignedShort() throws IOException {
        return 0;
    }

    @Override
    public char readChar() throws IOException {
        return 0;
    }

    @Override
    public int readInt() throws IOException {
        return 0;
    }

    @Override
    public long readLong() throws IOException {
        return 0;
    }

    @Override
    public float readFloat() throws IOException {
        return 0;
    }

    @Override
    public double readDouble() throws IOException {
        return 0;
    }

    @Override
    public String readLine() throws IOException {
        return null;
    }

    @Override
    public String readUTF() throws IOException {
        return null;
    }
}
