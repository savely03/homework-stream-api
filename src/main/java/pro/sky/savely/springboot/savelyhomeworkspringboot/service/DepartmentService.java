package pro.sky.savely.springboot.savelyhomeworkspringboot.service;

import pro.sky.savely.springboot.savelyhomeworkspringboot.models.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    Employee findEmployeeWithMaxSalaryByDepartment(int department);

    Employee findEmployeeWithMinSalaryByDepartment(int department);


    List<Employee> findAllEmployeesByDepartment(int department);

    Map<Integer, List<Employee>> findAllEmployeesGroupByDepartment();

}