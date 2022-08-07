package com.design.patterns.demo.designpattern.structure.composite.humanresource;

/**
 * Employee类
 *
 * @author wangjixue
 * @date 8/7/22 6:59 PM
 */
public class Employee extends HumanResource{

    public Employee(long id, double salary) {
        super(id);
        this.salary = salary;
    }

    @Override
    public double calculateSalary() {
        return salary;
    }
}
