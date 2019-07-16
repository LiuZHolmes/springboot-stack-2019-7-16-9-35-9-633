package com.tw.apistackbase.Repository;

import com.tw.apistackbase.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeRepository {
    public EmployeeRepository() {
        this.employees = new ArrayList<>();
    }

    private List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }
}