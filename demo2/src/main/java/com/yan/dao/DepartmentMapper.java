package com.yan.dao;

import com.yan.bean.Department;

public interface DepartmentMapper {
	
	Department getDeptById(Integer id);
	
	Department getDeptByIdPlus(Integer id);

	Department getDeptByIdStep(Integer id);
}
