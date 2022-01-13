import com.yan.bean.Department;
import com.yan.bean.Employee;
import com.yan.dao.DepartmentMapper;
import com.yan.dao.EmployeeMapper;
import com.yan.dao.EmployeeMapperPlus;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelloTest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }


    @Test
    public void t1() throws IOException {
        // 1、获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        // 2、获取sqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            // 3、获取接口的实现类对象
            //会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmpById(1);
            System.out.println(employee);
        } finally {
            openSession.close();
        }
    }

    @Test
    // EmployeeMapperPlus.class
    public void t2() throws IOException {

        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);

            // getEmpAndDept
/*            Employee employee = mapper.getEmpAndDept(1);
            System.out.println(employee);*/

            // getEmpByIdStep
/*            Employee employee = mapper.getEmpByIdStep(1);
            System.out.println(employee);*/

            // 延迟加载
/*            Employee employee = mapper.getEmpByIdStep(1);
            System.out.println(employee.getLastName());*/

            // getEmpsByDeptId
/*            List<Employee> emps = mapper.getEmpsByDeptId(1);
            for (Employee employee : emps){
                System.out.println(employee);
            }*/

        } finally {
            openSession.close();
        }
    }

    @Test
    // DepartmentMapper.class
    public void t3() throws IOException {

        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);

            // getDeptByIdPlus
/*            Department department = mapper.getDeptByIdPlus(1);
            System.out.println(department);
            List<Employee> emps = department.getEmps();
            for (Employee employee : emps){
                System.out.println(employee);
            }*/

            // getDeptByIdStep
            Department dept = mapper.getDeptByIdStep(1);
            System.out.println(dept);
            List<Employee> emps = dept.getEmps();
            for (Employee employee : emps){
                System.out.println(employee);
            };

        } finally {
            openSession.close();
        }
    }



}
