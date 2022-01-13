import com.yan.bean.Department;
import com.yan.bean.Employee;
import com.yan.dao.EmployeeMapperDynamicSQL;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyBatisTest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void t6() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
            List<Employee> emps = new ArrayList();
            emps.add(new Employee(null, "丁磊", "ding@163.com", "男",new Department(2)));
            //emps.add(new Employee(null, "董明珠", "zhu@geli.com", "女",new Department(1)));
            mapper.addEmps(emps);
            openSession.commit();

        } finally {
            openSession.close();
        }
    }

    @Test
    public void t5() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
            List<Employee> emps = mapper.getEmpsByConditionForeach(Arrays.asList(1, 2, 3));
            for (Employee emp : emps){
                System.out.println(emp);
            }
        } finally {
            openSession.close();
        }
    }

    @Test
    public void t4() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
            Employee employee = new Employee(2, "麻花疼", null, null);
            mapper.updateEmp(employee);
            openSession.commit();

        } finally {
            openSession.close();
        }
    }

    @Test
    // getEmpsByConditionChoose
    public void t3() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
            Employee employee = new Employee(2, "江%", null, null);
            List<Employee> emps = mapper.getEmpsByConditionChoose(employee);
            for (Employee emp : emps){
                System.out.println(emp);
            }

        } finally {
            openSession.close();
        }
    }

    @Test
    // getEmpsByConditionTrim
    public void t2() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
            Employee employee = new Employee(null, "马%", null, "男");
            List<Employee> emps = mapper.getEmpsByConditionTrim(employee);
            for (Employee emp : emps){
                System.out.println(emp);
            }

        } finally {
            openSession.close();
        }
    }

    @Test
    // getEmpsByConditionIf
    public void t1() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
            Employee employee = new Employee(null, "马%", null, "男");
            List<Employee> emps = mapper.getEmpsByConditionIf(employee);
            for (Employee emp : emps){
                System.out.println(emp);
            }

        } finally {
            openSession.close();
        }
    }

}
