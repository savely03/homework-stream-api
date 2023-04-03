package pro.sky.savely.springboot.savelyhomeworkspringboot.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pro.sky.savely.springboot.savelyhomeworkspringboot.exceptions.EmployeeAlreadyAddedException;
import pro.sky.savely.springboot.savelyhomeworkspringboot.exceptions.EmployeeNotFoundException;
import pro.sky.savely.springboot.savelyhomeworkspringboot.exceptions.EmployeeStoragelsFullException;
import pro.sky.savely.springboot.savelyhomeworkspringboot.models.Employee;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class EmployeeServiceListImpl implements EmployeeService {
    private final List<Employee> employees = new ArrayList<>(10);


    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Работник уже существует");
        }
        if (employees.size() == 10) {
            throw new EmployeeStoragelsFullException("Нет свободного места для добавления нового работника");
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
