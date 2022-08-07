package com.design.patterns.demo.designpattern.structure.composite.humanresource;

import java.util.ArrayList;
import java.util.List;

/**
 * Department类
 *
 * @author wangjixue
 * @date 8/7/22 6:58 PM
 */
public class Department extends HumanResource{
    private List<HumanResource> subNodes = new ArrayList<>();

    public Department(long id) {
        super(id);
    }

    @Override
    public double calculateSalary() {
        double totalSalary = 0;
        for (HumanResource subNode : subNodes) {
            totalSalary += subNode.calculateSalary();
        }
        this.salary = totalSalary;
        return totalSalary;
    }

    public void addSubNode(HumanResource hr){
        subNodes.add(hr);
    }

}
