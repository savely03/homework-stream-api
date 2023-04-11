package pro.sky.savely.springboot.savelyhomeworkspringboot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.savely.springboot.savelyhomeworkspringboot.exceptions.IncorrectDepartmentException;
import pro.sky.savely.springboot.savelyhomeworkspringboot.exceptions.IncorrectData;
import pro.sky.savely.springboot.savelyhomeworkspringboot.models.Employee;
import pro.sky.savely.springboot.savelyhomeworkspringboot.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Employee findEmployeeWithMaxSalaryByDepartment(@RequestParam("departmentId") int department) {
        return departmentService.findEmployeeWithMaxSalaryByDepartment(department);
    }

    @GetMapping("/min-salary")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Employee findEmployeeWithMinSalaryByDepartment(@RequestParam("departmentId") int department) {
        return departmentService.findEmployeeWithMinSalaryByDepartment(department);
    }

    @GetMapping("/all")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public List<Employee> findAllEmployeesByDepartment(@RequestParam("departmentId") int department) {
        return departmentService.findAllEmployeesByDepartment(department);
    }

    @GetMapping("/group-all")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Map<Integer, List<Employee>> findAllEmployeesGroupByDepartment() {
        return departmentService.findAllEmployeesGroupByDepartment();
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(IncorrectDepartmentException exception) {
        IncorrectData data = new IncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
