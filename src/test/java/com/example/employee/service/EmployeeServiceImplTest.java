package com.example.employee.service;

import com.example.employee.dao.EmployeeRepository;
import com.example.employee.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeServiceImpl(employeeRepository);
    }

    @Test
    void findAll() {
        employeeService.findAll();
        verify(employeeRepository).findAll();
    }

    @Test
    void findById() {
    }

    @Test
    void findByLastName() {
        employeeService.findByLastName();
        verify(employeeRepository).findByLastName();
    }

    @Test
    void findBySalary() {
    }

    @Test
    void findByEmail() {
        given(employeeRepository.findByEmail("dogan@test.com")).willReturn(Optional.empty());
        assertNull(employeeService.findByEmail("dogan@test.com"));

        Employee employee = new Employee();
        employee.setFirstName("Dogaancan");
        employee.setEmail("dogan@test.com");
        given(employeeRepository.findByEmail("dogan@test.com")).willReturn(Optional.of(employee));

        Employee foundEmployee = employeeService.findByEmail("dogan@test.com");
        assertNotNull(foundEmployee);
        assertEquals("Dogaancan", foundEmployee.getFirstName());
    }

    @Test
    void saveSuccess() {
        Employee employee = new Employee();
        employee.setEmail("deneme@test.com");
        given(employeeRepository.findByEmail("deneme@test.com")).willReturn(Optional.of(employee));
        given(employeeRepository.save(employee)).willReturn(employee);
        Employee savedEmployee = employeeService.save(employee);
        assertNotNull(savedEmployee);
        verify(employeeRepository).save(employee);
    }
    @Test
    void saveFailed(){
        Employee employee = new Employee();
        employee.setEmail("deneme@test.com");

        given(employeeRepository.findByEmail("deneme@test.com")).willReturn(Optional.of(employee));
        Employee savedEmployee = employeeService.save(employee);
        assertNull(savedEmployee);
        verify(employeeRepository, never()).save(employee);
    }

    @Test
    void delete() {
    }
}