package pro.sky.savely.springboot.savelyhomeworkspringboot.service;

import pro.sky.savely.springboot.savelyhomeworkspringboot.models.Employee;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName, int department, int salary);

    Employee removeEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);


    List<Employee> findAllEmployees();
}

