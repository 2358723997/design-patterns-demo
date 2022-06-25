package com.design.patterns.demo.designpattern.create.singleton.counterexample;

/**
 * NonsupportCase类
 * 单例不支持有参数的构造函数，
 *
 * 比如我们创建一个连接池的单例对象，我们没法通过参数来指定连接池的大小
 *
 * 解决方法：
 * 1. 创建完实例之后，再调用 init() 函数传递参数。
 * 需要注意的是，我们在使用这个单例类的时候，要先调用 init() 方法，然后
 * 才能调用 getInstance() 方法，否则代码会抛出异常
 *
 * 2. 将参数放到 getIntance() 方法中。
 *
 * 3. 将参数放到另外一个全局变量中（推荐）。
 *
 * @author wangjixue
 * @date 6/25/22 11:12 PM
 */
public class NonsupportCase {
    public static void main(String[] args) {
        //方案一:先调用init(),在使用。
        SingletonA.init(10, 20);
        SingletonA instance = SingletonA.getInstance();

        //方案二：存在问题，仅第一次传入的参数其作用，其他的调用传入的参数
        SingletonB.getInstance(20, 20);
        //方案三：
        SingletonC.getInstance();
    }

}
