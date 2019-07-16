package com.tw.apistackbase.Class;

import com.tw.apistackbase.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class Company {

    public Company() {
        this.employeeRepository = new EmployeeRepository();
    }

    private EmployeeRepository employeeRepository;

    private long companyID;

    private String companyName;

    public int getEmployeesNumber() {
        employeesNumber = employeeRepository.getEmployees().size();
        return employeesNumber;
    }

    private int employeesNumber;

    public long getCompanyID() {
        return companyID;
    }

    public void setCompanyID(long companyID) {
        this.companyID = companyID;
    }


    public EmployeeRepository getEmployeeRepository() {
        return employeeRepository;
    }

    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public String getName() {
        return companyName;
    }

    public void setName(String name) {
        this.companyName = name;
    }
}
