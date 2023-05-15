package pro.sky.savely.springboot.savelyhomeworkspringboot.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pro.sky.savely.springboot.savelyhomeworkspringboot.exceptions.*;
import pro.sky.savely.springboot.savelyhomeworkspringboot.models.Employee;
import pro.sky.savely.springboot.savelyhomeworkspringboot.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public List<Employee> findAllEmployees() {
        return employeeService.findAllEmployees();
    }

    @GetMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Employee addEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                                @RequestParam("department") int department, @RequestParam("salary") int salary) {
        return employeeService.addEmployee(firstName, lastName, department, salary);
    }

    @GetMapping("/remove")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Employee removeEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return employeeService.removeEmployee(firstName, lastName);
    }

    @GetMapping("/find")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Employee findEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return employeeService.findEmployee(firstName, lastName);
    }


    @ExceptionHandler(EmployeeNotFoundException.class)
    public IncorrectData handleException(EmployeeNotFoundException exception) {
        IncorrectData data = new IncorrectData();
        data.setInfo(exception.getMessage());
        return data;
    }

    @ExceptionHandler(EmployeeAlreadyAddedException.class)
    public IncorrectData handleException(EmployeeAlreadyAddedException exception) {
        IncorrectData data = new IncorrectData();
        data.setInfo(exception.getMessage());
        return data;
    }

    @ExceptionHandler(EmployeeStoragelsFullException.class)
    public IncorrectData handleException(EmployeeStoragelsFullException exception) {
        IncorrectData data = new IncorrectData();
        data.setInfo(exception.getMessage());
        return data;
    }

    @ExceptionHandler(IncorrectArgumentException.class)
    public IncorrectData handleException(IncorrectArgumentException exception) {
        IncorrectData data = new IncorrectData();
        data.setInfo(exception.getMessage());
        return data;
    }
}
