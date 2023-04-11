package pro.sky.savely.springboot.savelyhomeworkspringboot.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pro.sky.savely.springboot.savelyhomeworkspringboot.exceptions.EmployeeAlreadyAddedException;
import pro.sky.savely.springboot.savelyhomeworkspringboot.exceptions.IncorrectDepartmentException;
import pro.sky.savely.springboot.savelyhomeworkspringboot.exceptions.EmployeeNotFoundException;
import pro.sky.savely.springboot.savelyhomeworkspringboot.exceptions.EmployeeStoragelsFullException;
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

    @Override
    public Employee addEmployee(String firstName, String lastName, int department, int salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
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
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException("Работник не найден");
        }
        employees.remove(employee);
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
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

