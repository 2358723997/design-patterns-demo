package com.design.patterns.demo.designpattern.structure.composite.humanresource;

import java.util.List;

import com.design.patterns.demo.designpattern.structure.composite.humanresource.dao.DepartmentDao;
import com.design.patterns.demo.designpattern.structure.composite.humanresource.dao.EmployeeDao;

/**
 * Demo类
 *
 * @author wangjixue
 * @date 8/7/22 6:54 PM
 */
// 构建组织架构的代码
public class Demo {
    private static final long ORGANIZATION_ROOT_ID = 1001;

    //依赖注入
    private DepartmentDao departmentDao;
    private EmployeeDao employeeDao;

    public void buildOrganization(){
        Department rootDepartment = new Department(ORGANIZATION_ROOT_ID);
        buildOrganization(rootDepartment);
    }

    private void buildOrganization(Department department){
        List<Long> subDepartmentIds =  departmentDao.getSubDepartmentIds(department.getId());
        for (Long subDepartmentId : subDepartmentIds) {
            Department subDepartment = new Department(subDepartmentId);
            department.addSubNode(subDepartment);
        }

        List<Long> employeeIds = employeeDao.getSubEmployeeIds(department.getId());
        for (Long employeeId : employeeIds) {
            double salary = employeeDao.getSalary(employeeId);
            department.addSubNode(new Employee(employeeId,salary));
        }
    }
}
