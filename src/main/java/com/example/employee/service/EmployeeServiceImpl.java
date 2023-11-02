package com.example.employee.service;

import com.example.employee.dao.EmployeeRepository;
import com.example.employee.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()){
            return optionalEmployee.get();
        }
        return null;
    }

    @Override
    public List<Employee> findByLastName() {
        return employeeRepository.findByLastName();
    }

    @Override
    public List<Employee> findBySalary(double salary) {
        return employeeRepository.findBySalary(salary);
    }

    @Override
    public Employee findByEmail(String email) {
        Optional<Employee> employeeOptional = employeeRepository.findByEmail(email);
        if(employeeOptional.isPresent()){
            return employeeOptional.get();
        }
        return null;
    }

    @Override
    public Employee save(Employee employee) {
        Employee savedEmployee = findById(employee.getId());
        if (savedEmployee != null){
            return null;
        }
        return employeeRepository.save(employee);
    }

    @Override
    public Employee delete(long id) {
        Employee employee = findById(id);
        employeeRepository.delete(employee);
        return employee;
    }
}
