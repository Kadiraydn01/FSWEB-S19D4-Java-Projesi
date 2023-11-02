package com.example.employee.dao;

import com.example.employee.entity.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EmployeeRepositoryTest {

    private EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeRepositoryTest(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @BeforeEach
    void setUp() {
        Employee employee1 = new Employee();
        employee1.setFirstName("Ali");
        employee1.setLastName("Veli");
        employee1.setEmail("ali@veli.com");
        employee1.setSalary(30000);

        Employee employee2 = new Employee();
        employee2.setFirstName("Ayşe");
        employee2.setLastName("Yaman");
        employee2.setEmail("ayse@kadir.com");
        employee2.setSalary(40000);

        Employee employee3 = new Employee();
        employee3.setFirstName("Mahmut");
        employee3.setLastName("Yakup");
        employee3.setEmail("mahmut@yakup.com");
        employee3.setSalary(50000);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);
        employeeRepository.saveAll(employeeList);
    }
    @AfterEach
    void tearDown(){
        employeeRepository.deleteAll();
    }

    @Test
    void findByEmail() {
        String nonExistingMail = "kadirrr@test.com";
        Optional<Employee> optionalEmployee = employeeRepository.findByEmail(nonExistingMail);
        assertEquals(Optional.empty(),optionalEmployee);

        String existingMail = "ayse@kadir.com";
        Optional<Employee> exist = employeeRepository.findByEmail(existingMail);
        assertNotNull(exist.get());
        assertEquals("Ayşe",exist.get().getFirstName());
        assertEquals(40000,exist.get().getSalary());
    }

    @Test
    void findBySalary() {
        List<Employee> employeeList = employeeRepository.findBySalary(32000);
        assertEquals(2,employeeList.size());
        assertEquals("Mahmut",employeeList.get(0).getFirstName());
        assertEquals("Ayşe",employeeList.get(1).getFirstName());
    }

    @Test
    void findByLastName() {
        List<Employee> employeeList = employeeRepository.findByLastName();
        assertEquals(3,employeeList.size());
        assertEquals("Ali",employeeList.get(0).getFirstName());
        assertEquals("Mahmut",employeeList.get(1).getFirstName());
        assertEquals("Ayşe",employeeList.get(2).getFirstName());
    }
}