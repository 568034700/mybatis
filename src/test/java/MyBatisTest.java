import bean.Employee;
import dao.EmployeeMapper;
import dao.EmployeeMapperAnnotation;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author : caohong
 * @Date : 2019-05-30
 */
public class MyBatisTest {
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 1、根据xml配置文件（全局配置文件）创建一个SqlSessionFactory对象 有数据源一些运行环境信息
     * 2、sql映射文件；配置了每一个sql，以及sql的封装规则等。
     * 3、将sql映射文件注册在全局配置文件中
     * 4、写代码：
     * 1）、根据全局配置文件得到SqlSessionFactory；
     * 2）、使用sqlSession工厂，获取到sqlSession对象使用他来执行增删改查
     * 一个sqlSession就是代表和数据库的一次会话，用完关闭
     * 3）、使用sql的唯一标志来告诉MyBatis执行哪个sql。sql都是保存在sql映射文件中的。
     *
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //获取sqlSession实例
        //selectOne（）的第一个参数是sql的唯一标识：一般是namesapce + id
        //selectOne（）的第二个是执行的sql语句
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            Employee employee = openSession.selectOne("EmployeeMapper.selectEmp", 1);
            System.out.print(employee);
        } finally {
            openSession.close();
        }


    }

    @Test
    public void test01() throws IOException {
        // 1、获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        // 2、获取sqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            // 3、获取接口的实现类对象
            //会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmpById(1);
            System.out.println(mapper.getClass());
            System.out.println(employee);
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test02() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperAnnotation mapper = openSession.getMapper(EmployeeMapperAnnotation.class);
            Employee employee = mapper.getEmpById(1);
            System.out.println(employee);
        } finally {
            openSession.close();
        }

    }

    /**
     * 测试增删改
     * 1、mybatis允许增删改直接定义以下类型返回值
     * Integer、Long、Boolean、void
     * 2、我们需要手动提交数据
     * sqlSessionFactory.openSession();===》手动提交
     * sqlSessionFactory.openSession(true);===》自动提交
     *
     * @throws IOException
     */
    @Test
    public void test03() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
//        1、获取到的SqlSession不会自动提交数据
        SqlSession openSession = sqlSessionFactory.openSession();
        Employee employee = new Employee(3, "jerry", "123@qq.com", "2");
        Employee employee1 = new Employee(100, "jerry", "123@qq.com", "2");


        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            //测试添加
//            mapper.addEmp(employee);
            //测试修改
//            mapper.updateEmp(employee);
            //测试删除
//              mapper.deleteEmpById(101);
            //测试主键自增
            mapper.addEmpAuto(employee1);
            System.out.println(employee1.getId());


            openSession.commit();
        } finally {
            openSession.close();
        }


    }


    @Test
    public void test04() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
//        1、获取到的SqlSession不会自动提交数据
        SqlSession openSession = sqlSessionFactory.openSession();


        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmpByIdAndLastName((1), "tom");
            System.out.println(employee);
            openSession.commit();
        } finally {
            openSession.close();
        }


    }

    @Test
    public void test05() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
//        1、获取到的SqlSession不会自动提交数据
        SqlSession openSession = sqlSessionFactory.openSession();


        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", 1);
            map.put("lastName", "tom");
            map.put("tableName", "tbl_employee");
            Employee employee = mapper.getEmpByMap(map);
            System.out.println(employee);
            openSession.commit();
        } finally {
            openSession.close();
        }


    }

    @Test
    public void test07() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
//        1、获取到的SqlSession不会自动提交数据
        SqlSession openSession = sqlSessionFactory.openSession();


        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            List<Integer> list11 = new ArrayList<Integer>();
            list11.add(1);
            Employee employee = mapper.getEmpByList(list11);
            System.out.println(employee);
            openSession.commit();
        } finally {
            openSession.close();
        }


    }

    @Test
    public void test08() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
//        1、获取到的SqlSession不会自动提交数据
        SqlSession openSession = sqlSessionFactory.openSession();


        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            List<Integer> list11 = new ArrayList<Integer>();
            list11.add(1);
            Employee employee = mapper.getEmpByList(list11);
            System.out.println(employee);
            openSession.commit();
        } finally {
            openSession.close();
        }


    }

    @Test
    public void test09() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
//        1、获取到的SqlSession不会自动提交数据
        SqlSession openSession = sqlSessionFactory.openSession();


        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            List<Integer> list11 = new ArrayList<Integer>();
            list11.add(1);
            List<String> list22 = new ArrayList<String>();
            list22.add("tom");
            Employee employee = mapper.getEmpByListParam2(list11, list22);
            System.out.println(employee);
            openSession.commit();
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test10() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
//        1、获取到的SqlSession不会自动提交数据
        SqlSession openSession = sqlSessionFactory.openSession();


        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", 1);
            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("lastName", "tom");
            List<String> list22 = new ArrayList<String>();
            list22.add("tom");
            Employee employee = mapper.getEmpByMap2(map, map2);
            System.out.println(employee);
            openSession.commit();
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test11() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();

        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            List<Employee> like = mapper.getEmpsByLastNameLike("j");
            for (Employee e : like) {
                System.out.println(e);
            }
            openSession.commit();
        } finally {
            openSession.close();
        }

    }

    @Test
    public void test12() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();

        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Map<String, Object> map = mapper.getEmpByIdReturnMap(1);
            System.out.println(map);
        } finally {
            openSession.close();
        }

    }

    @Test
    public void test13() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();

        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Map<String, Employee> map = mapper.getEmpByLastNameLikeReturnMap("j");
            System.out.println(map);
        } finally {
            openSession.close();
        }

    }

}
