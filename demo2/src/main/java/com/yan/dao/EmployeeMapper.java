package com.yan.dao;

import com.yan.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {

    Employee getEmpById(Integer id);

    Long addEmp(Employee employee);

    boolean updateEmp(Employee employee);

    void deleteEmpById(Integer id);

    Employee getEmpByIdAndLastName(@Param("idName")Integer id,@Param("lastName")String name);

    Employee getEmpMap(Map<String, Object> map);

    List<Employee> getEmpByLastNameLike(String lastName);

    //多条记录封装一个map：Map<Integer,Employee>:键是这条记录的主键，值是记录封装后的javaBean
    //@MapKey:告诉mybatis封装这个map的时候使用哪个属性作为map的key
    @MapKey("lastName")
    Map<String, Employee> getEmpByLastNameLikeReturnMap(String lastName);

    //返回一条记录的map；key就是列名，值就是对应的值
    Map<String, Object> getEmpByIdReturnMap(Integer id);
}
