package com.design.patterns.demo.designpattern.structure.decorator;

import java.io.IOException;

/**
 * MyInputStream接口
 * 针对继承结构过于复杂的问题(PS：继承超过3层)，
 * 我们可以通过将继承关系改为组合关系来解决。
 *
 * @author wangjixue
 * @date 7/13/22 11:10 PM
 */
public abstract class MyInputStream {

    public int read(byte b[]) throws IOException {
        return read(b, 0, b.length);
    }

    public int read(byte[] b, int offset, int length) throws IOException {
        //TODO 真实处理逻辑
        return 0;
    }

    public long skip(long n)throws IOException{
        //TODO 真实处理逻辑
        return 0;
    }

    public int available()throws IOException{
        //TODO 真实处理逻辑
        return 0;
    }

    public void close()throws IOException{
        //TODO 真实处理逻辑,下面相似的TODO
    }

    public synchronized void mark(int readLimit)throws IOException{

    }

    public synchronized void reset(int readLimit)throws IOException{

    }

    public boolean markSupported(){
        return false;
    }

}
