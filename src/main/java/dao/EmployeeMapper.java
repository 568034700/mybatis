package dao;

import bean.Employee;

public interface EmployeeMapper {
    public Employee getEmpById(Integer id);

    public Long addEmp(Employee employee);

    public boolean updateEmp(Employee employee);

    public void deleteEmpById(Integer id);

    public long addEmpAuto(Employee employee);
}
