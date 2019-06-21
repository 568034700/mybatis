package dao;

import bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {

    //多条记录封装一个map：Map<Integer,Employee>:键是这条记录的主键，值是记录封装后的javaBean
    //@MapKey:告诉mybatis封装这个map的时候使用哪个属性作为map的key
    @MapKey("lastName")
    public Map<String, Employee> getEmpByLastNameLikeReturnMap(String lastName);

    //返回一条记录的map:key就是列名，值就是对应的值
    public Map<String,Object> getEmpByIdReturnMap(Integer id);

//    <!--resultType：如果返回的是一个集合，要写集合中元素的类型  -->
    public List<Employee> getEmpsByLastNameLike(String lastName);

    public Employee getEmpByListParam2(@Param("myList1") List<Integer> ids,@Param("myList2")List<String> names);

    public Employee getEmpByListParam(@Param("myList") List<Integer> ids);

    public Employee getEmpByList( List<Integer> ids);

    public Employee getEmpByMap2(Map<String, Object> map1,Map<String,Object> map2);

    public Employee getEmpByMap(Map<String, Object> map);

    public Employee getEmpByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);

    public Employee getEmpById(Integer id);

    public Long addEmp(Employee employee);

    public boolean updateEmp(Employee employee);

    public void deleteEmpById(Integer id);

    public long addEmpAuto(Employee employee);
}
