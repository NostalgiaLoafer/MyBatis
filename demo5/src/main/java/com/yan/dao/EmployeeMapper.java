package com.yan.dao;

import com.yan.bean.Employee;

import java.util.List;

public interface EmployeeMapper {
    Employee getEmpById(Integer id);

    List<Employee> getEmps();
}
