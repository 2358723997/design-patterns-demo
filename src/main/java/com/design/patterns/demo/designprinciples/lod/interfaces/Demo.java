package com.design.patterns.demo.designprinciples.lod.interfaces;

/**
 * Demo类
 *
 *  需求：假设在我们的项目中，有些类只用到了序列化操作，而另一些类只用到反序列化操作。
 *  那基于迪米特法则后半部分“有依赖关系的类之间，尽量只依赖必要的接口”，只用到序列化操作的那部分类不应该依赖反序列化接口。
 *  同理，只用到反序列化操作的那部分类不应该依赖序列化接口。
 *  如果分别设计两个类来实现上述功能又会违背了高内聚的设计思想。
 *  解决思路：分别设计两个接口，然后让Serialization类实现两个接口即可，该代码实现思路，也体现了“基于接口而非实现编程”
 *  的设计原则，结合迪米特法则，我们可以总结出一条新的设计原则，那就是“基于最小接口而非最大实现编程”。
 * @author wangjixue
 * @date 1/3/22 11:19 PM
 */
public class Demo {

    public static void main(String[] args) {
        //序列化
        Serializable serializable = new Serialization();
        serializable.serialize(new Object());
        //反序列化
        Deserializable deserializable = new Serialization();
        deserializable.deserialize("");
    }
}
