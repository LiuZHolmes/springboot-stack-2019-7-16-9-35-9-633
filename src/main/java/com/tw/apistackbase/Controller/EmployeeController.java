package com.tw.apistackbase.Controller;

import com.tw.apistackbase.Employee;
import com.tw.apistackbase.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> listCompanies() {
        return employeeRepository.getEmployees();
    }
}
