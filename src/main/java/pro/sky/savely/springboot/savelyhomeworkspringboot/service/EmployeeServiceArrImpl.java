package pro.sky.savely.springboot.savelyhomeworkspringboot.service;

import org.springframework.stereotype.Service;
import pro.sky.savely.springboot.savelyhomeworkspringboot.exceptions.EmployeeAlreadyAddedException;
import pro.sky.savely.springboot.savelyhomeworkspringboot.exceptions.EmployeeNotFoundException;
import pro.sky.savely.springboot.savelyhomeworkspringboot.exceptions.EmployeeStoragelsFullException;
import pro.sky.savely.springboot.savelyhomeworkspringboot.models.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceArrImpl implements EmployeeService {


    private final Employee[] employees = new Employee[10];


    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                employees[i] = employee;
                return employee;
            }
            if (employees[i].equals(employee)) {
                throw new EmployeeAlreadyAddedException("Работник уже добавлен!");
            }
        }
        throw new EmployeeStoragelsFullException("Массив работников переполнен!");
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].equals(employee)) {
                employees[i] = null;
                return employee;
            }
        }
        throw new EmployeeNotFoundException("Работник не может быть удален!");
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        for (Employee emp : employees) {
            if (emp != null && emp.equals(employee)) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException("Работник не существует!");
    }

    @Override
    public List<Employee> findAllEmployees() {
        return Arrays.stream(employees).filter(Objects::nonNull).collect(Collectors.toList());
    }
}
