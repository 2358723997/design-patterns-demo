package com.design.patterns.demo.designpattern.structure.composite.humanresource;

import java.util.ArrayList;
import java.util.List;

/**
 * HumanResource类
 *
 * 假设我们在开发一个 OA 系统（办公自动化系统）。公司的组织结构包含部门和员工两种数据类型。
 * 其中，部门又可以包含子部门和员工。
 *
 * 我们希望在内存中构建整个公司的人员架构图（部门、子部门、员工的隶属关系），并且提供接口计
 * 算出部门的薪资成本（隶属于这个部门的所有员工的薪资和）。部门包含子部门和员工，这是一种嵌
 * 套结构，可以表示成树这种数据结构。计算每个部门的薪资开支这样一个需求，也可以通过在树上的
 * 遍历算法来实现。
 *
 * 所以，从这个角度来看，这个应用场景可以使用组合模式来设计和实现。
 *
 * @author wangjixue
 * @date 8/7/22 6:45 PM
 */
public abstract class HumanResource {
    protected long id;
    protected double salary;

    public HumanResource(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public abstract double calculateSalary();
}
