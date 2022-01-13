package com.yan.dao;

import java.util.List;

import com.yan.bean.Employee;

public interface EmployeeMapperPlus {
	
	Employee getEmpById(Integer id);
	
	Employee getEmpAndDept(Integer id);
	
	Employee getEmpByIdStep(Integer id);
	
	List<Employee> getEmpsByDeptId(Integer deptId);

}
