package com.example.employee.service;

import com.example.employee.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> findAll();
    List<Employee> findByLastName();
    List<Employee>findBySalary(double salary);
    Employee findById(long id);
    Employee findByEmail(String email);
    Employee save(Employee employee);
    Employee delete(long id);
}
