import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yan.bean.Employee;
import com.yan.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void t1() throws IOException {

        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        SqlSession openSession = sqlSessionFactory.openSession();
        try {

            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);

            PageHelper.startPage(3, 1);

            List<Employee> emps = mapper.getEmps();

            PageInfo<Object> info = new PageInfo(emps, 3);

            for (Employee employee : emps){
                System.out.println(employee);
            }
            System.out.println("当前页码："+info.getPageNum());
            System.out.println("总记录数："+info.getTotal());
            System.out.println("每页的记录数："+info.getPageSize());
            System.out.println("总页码："+info.getPages());
            System.out.println("是否第一页："+info.isIsFirstPage());
            System.out.println("连续显示的页码：");
            int[] nums = info.getNavigatepageNums();
            for (int i = 0; i < nums.length; i++) {
                System.out.println(nums[i]);
            }
        } finally {
            openSession.close();
        }
    }
}
