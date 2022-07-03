package com.design.patterns.demo.designpattern.create.builder;

/**
 * BuilderDemo类
 *
 * @author wangjixue
 * @date 7/3/22 11:24 PM
 */
public class BuilderDemo {
    public static void main(String[] args) {
        //因为minIdel > maxIdel ,所以构建的时候会抛异常。
        ResourcePoolConfig.Builder builder = new ResourcePoolConfig.Builder()
            .setName("connectpool")
            .setMaxTotal(16)
            .setMaxIdel(10)
            .setMinIdel(12);
        ResourcePoolConfig config = builder.builder();
    }
}
