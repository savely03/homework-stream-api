package pro.sky.savely.springboot.savelyhomeworkspringboot.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.savely.springboot.savelyhomeworkspringboot.exceptions.EmployeeException;
import pro.sky.savely.springboot.savelyhomeworkspringboot.exceptions.IncorrectData;
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
    @ResponseStatus(code = HttpStatus.ACCEPTED)
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


    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(EmployeeException exception) {
        IncorrectData data = new IncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
