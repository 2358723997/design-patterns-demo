package com.design.patterns.demo.designpattern.create.singleton.type;

/**
 * IdTest类
 *
 * 单例设计模式：
 *
 * 定义：
 * 单例设计模式（Singleton Design Pattern）理解起来非常简单。
 * 一个类只允许创建一个对象（或者叫实例），那这个类就是一个单例类，这种设计模式就叫作单例设计模式，
 * 简称单例模式。
 *
 * 场景：
 * 从业务概念上，有些数据在系统中只应该保存一份，
 * 就比较适合设计为单例类。比如，系统的配置信息类。
 * 除此之外，我们还可以使用单例解决资源访问冲突的问题，比如全局ID生成器。
 *
 *
 * @author wangjixue
 * @date 6/19/22 11:53 PM
 */
public class IdGeneratorTest {
    public static void main(String[] args) {
        long id = HungryIdGenerator.getInstance().getId();
        id = LazyIdGenerator.getInstance().getId();
        id = DoubleDetectionIdGenerator.getInstance().getId();
        id = StaticInnerClassIdGenerator.getInstance().getId();
        id = EnumerateIdGenerator.INSTANCE.getId();
    }
}
