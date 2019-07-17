package com.tw.apistackbase.Controller;

import com.tw.apistackbase.Class.Employee;
import com.tw.apistackbase.Exception.EmployeeNotFoundException;
import com.tw.apistackbase.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> listCompanies() {
        return employeeRepository.getEmployees();
    }

    @GetMapping("/employees/{employeeID}")
    public Employee findCompanyByID(@PathVariable long employeeID) throws EmployeeNotFoundException {
        Optional<Employee> optionalEmployee = employeeRepository.getEmployees()
                .stream()
                .filter(x -> x.getEmployeeID() == employeeID)
                .findFirst();
        if (optionalEmployee.isPresent()) return optionalEmployee.get();
        else throw new EmployeeNotFoundException();
    }

    @GetMapping("/employees?page={page}&pageSize={pageSize}")
    public List<Employee> listCompaniesPageByPage(@PathVariable int page, @PathVariable int size) {
        return employeeRepository.getEmployees()
                .stream()
                .skip((page - 1) * size)
                .limit(size).collect(Collectors.toList());
    }

    @GetMapping("/employees?gender={gender}")
    public List<Employee> listCompaniesPageByPage(@PathVariable String gender) {
        return employeeRepository.getEmployees()
                .stream()
                .filter(x -> x.getGender() == gender)
                .collect(Collectors.toList());
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody String request) {
        Employee employee = new Employee();
        employee.setEmployeeID(1);
        employeeRepository.getEmployees().add(employee);
        return employee;
    }

    @PutMapping("/employees/{employeeID}")
    public Employee updateEmployee(@RequestBody Employee newEmployee, @PathVariable long employeeID) throws EmployeeNotFoundException {

        Optional<Employee> optionalEmployee = employeeRepository.getEmployees()
                .stream()
                .filter(x -> x.getEmployeeID() == employeeID)
                .findFirst();
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setGender(newEmployee.getGender());
            return employee;
        } else throw new EmployeeNotFoundException();
    }

    @DeleteMapping("/employees/{employeeID}")
    public ResponseEntity deleteEmployee(@PathVariable long employeeID) {
        employeeRepository.setEmployees(employeeRepository.getEmployees()
                .stream()
                .filter(x -> x.getEmployeeID() != employeeID)
                .collect(Collectors.toList())
        );
        return ResponseEntity.ok().build();
    }
}
