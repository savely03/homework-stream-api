package pro.sky.savely.springboot.savelyhomeworkspringboot.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pro.sky.savely.springboot.savelyhomeworkspringboot.exceptions.*;
import pro.sky.savely.springboot.savelyhomeworkspringboot.models.Employee;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class EmployeeServiceListImpl implements EmployeeService {
    private final List<Employee> employees;

    public EmployeeServiceListImpl() {
        employees = new ArrayList<>();
    }


    private void checkFirstNameAndLastName(String firstName, String lastName) {
        if (StringUtils.isBlank(firstName) || StringUtils.isBlank(lastName) ||
                !StringUtils.isAlpha(firstName + lastName)) {
            throw new IncorrectArgumentException("Проверьте правильность переданных данных");
        }
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, int department, int salary) {
        checkFirstNameAndLastName(firstName, lastName);
        Employee employee = new Employee(StringUtils.capitalize(firstName.toLowerCase()),
                StringUtils.capitalize(lastName.toLowerCase()), department, salary);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Работник уже существует");
        }
        if (employees.size() == 10) {
            throw new EmployeeStoragelsFullException("Нет свободного места для добавления нового работника");
        }
        if (employee.getDepartment() < 1 || employee.getDepartment() > 5) {
            throw new IncorrectDepartmentException("Номер департамента должен быть от 1 до 5!");
        }
        employees.add(employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        checkFirstNameAndLastName(firstName, lastName);
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException("Работник не найден");
        }
        employees.remove(employee);
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        checkFirstNameAndLastName(firstName, lastName);
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException("Работник не найден");
        }
        return employee;
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employees;
    }
}

