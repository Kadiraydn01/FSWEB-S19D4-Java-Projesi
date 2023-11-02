package com.example.employee.controller;

import com.example.employee.dto.EmployeeResponse;
import com.example.employee.entity.Employee;
import com.example.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/")
    public List<Employee>findAll(){
        return employeeService.findAll();
    }


    @GetMapping("/{id}")
    public EmployeeResponse findById(@PathVariable long id){
        Employee employee = employeeService.findById(id);
        return new EmployeeResponse(employee.getId(), employee.getFirstName(), employee.getEmail());
    }
    @GetMapping("/order")
    public List<Employee> findByOrder(){
        return employeeService.findByLastName();
    }
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee save(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @DeleteMapping("/{id}")
    public Employee delete (@PathVariable long id){
        return employeeService.delete(id);
    }
}
