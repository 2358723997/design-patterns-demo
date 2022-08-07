package com.design.patterns.demo.designpattern.structure.composite.humanresource.dao;

import java.util.Collections;
import java.util.List;

/**
 * EmployeeDao类
 *
 * @author wangjixue
 * @date 8/7/22 7:06 PM
 */
public class EmployeeDao {
    public List<Long> getSubEmployeeIds(long id) {
        return Collections.emptyList();
    }

    public double getSalary(Long employeeId) {
        return 0;
    }
}
