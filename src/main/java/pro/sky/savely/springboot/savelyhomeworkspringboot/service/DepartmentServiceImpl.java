package pro.sky.savely.springboot.savelyhomeworkspringboot.service;

import org.springframework.stereotype.Service;
import pro.sky.savely.springboot.savelyhomeworkspringboot.models.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee findEmployeeWithMaxSalaryByDepartment(int department) {
        return employeeService.findAllEmployees().stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparing(Employee::getSalary)).orElse(null);
    }

    @Override
    public Employee findEmployeeWithMinSalaryByDepartment(int department) {
        return employeeService.findAllEmployees().stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparing(Employee::getSalary)).orElse(null);
    }

    @Override
    public List<Employee> findAllEmployeesByDepartment(int department) {
        return employeeService.findAllEmployees().stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> findAllEmployeesGroupByDepartment() {
        return employeeService.findAllEmployees().stream().collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
