package com.tw.apistackbase.Class;

import com.tw.apistackbase.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class Company {

    public long getCompanyID() {
        return companyID;
    }

    public void setCompanyID(long companyID) {
        this.companyID = companyID;
    }

    private long companyID;

    public EmployeeRepository getEmployeeRepository() {
        return employeeRepository;
    }

    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Autowired
    private EmployeeRepository employeeRepository;
}
