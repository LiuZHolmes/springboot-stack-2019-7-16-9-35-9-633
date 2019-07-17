package com.tw.apistackbase.Class;

import com.tw.apistackbase.Repository.EmployeeRepository;

public class Company {

    private String companyName;

    private long companyID;

    private int employeesNumber;

    private EmployeeRepository employeeRepository;

    public Company(long companyID) {
        this.employeeRepository = new EmployeeRepository();
        this.companyID = companyID;
    }

    public Company() {
        this.employeeRepository = new EmployeeRepository();
    }


    public int getEmployeesNumber() {
        employeesNumber = employeeRepository.getEmployees().size();
        return employeesNumber;
    }


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
