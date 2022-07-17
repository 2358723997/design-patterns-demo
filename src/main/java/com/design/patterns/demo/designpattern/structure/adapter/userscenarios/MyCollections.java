package com.design.patterns.demo.designpattern.structure.adapter.userscenarios;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * MyCollections类
 * 使用场景4：兼容老版本接口
 *
 * 在做版本升级的时候，对于一些要废弃的接口，我们不直接将其删除，
 * 而是暂时保留，并且标注为 deprecated，并将内部实现逻辑委托为
 * 新的接口实现。这样做的好处是，让使用它的项目有个过渡期，而不是
 * 强制进行代码修改。这也可以粗略地看作适配器模式的一个应用场景。
 *
 * @author wangjixue
 * @date 7/17/22 6:29 PM
 */
//JDK1.0 中包含一个遍历集合容器的类 Enumeration。JDK2.0 对这个
// 类进行了重构，将它改名为 Iterator 类，并且对它的代码实现做了优化。
// 但是考虑到如果将 Enumeration 直接从JDK2.0中删除，那使用JDK1.0
// 的项目如果切换到JDK2.0，代码就会编译不通过。为了避免这种情况的发生
// 我们可以暂时保留 Enumeration 类，并将其实现替换为直接调用 Itertor
public class MyCollections {
    public static Emueration emumeration(final Collection c) {
        return new Emueration(){
            Iterator i = c.iterator();
            public boolean hasMoreElments(){
                return i.hasNext();
            }

            public Object nextElement(){
                return i.next()
            }
        };
    }

}

/**
 * 使用场景5：适配不同格式的数据
 *
 * 适配器模式主要用于接口的适配，
 * 实际上，它还可以用在不同格式的数据之间的适配。
 * 比如，把从不同征信系统拉取的不同格式的征信数据，统一为相同的格式，以方便存储和使用。
 * 再比如，Java中的Arrays.asList()也可以看作一种数据适配器，
 * 将数组类型的数据转化为集合容器类型。
 **/
class Demo5{
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("12", "34");
    }
}