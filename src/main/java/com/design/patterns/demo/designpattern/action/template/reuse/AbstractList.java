package com.design.patterns.demo.designpattern.action.template.reuse;

import java.util.Collection;

/**
 * AbstractList类
 *
 * 在 Java AbstractList 类中，addAll() 函数可以看作模板方法，
 * add() 是子类需要重写的方法，尽管没有声明为 abstract 的，
 * 但函数实现直接抛出了 UnsupportedOperationException 异常。
 * 前提是，如果子类不重写是不能使用的。
 *
 * @author wangjixue
 * @date 2022/9/3 2:34 PM
 */
public abstract class AbstractList {

    public <E> boolean addAll(int index, Collection<? extends E> c){
        rangeCheckForAdd(index);
        boolean modified = false;
        for (E e : c) {
            add(index++,e);
            modified = true;
        }

        return modified;
    }

    private <E> void add(int i, E e) {
        throw new UnsupportedOperationException();
    }

    private void rangeCheckForAdd(int index) {
    }
}
