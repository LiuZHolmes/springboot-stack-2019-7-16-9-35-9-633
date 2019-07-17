package com.tw.apistackbase.Class;

import com.tw.apistackbase.Repository.EmployeeRepository;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Company {


    @Id
    @GeneratedValue
    private long companyID;
    private String companyName;
    private int employeesNumber;

    // @OneToMany(cascade = CascadeType.ALL)
    @Transient
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        Company company = (Company) o;
        return getCompanyID() == company.getCompanyID() &&
                getEmployeesNumber() == company.getEmployeesNumber() &&
                Objects.equals(getCompanyName(), company.getCompanyName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCompanyID(), getCompanyName(), getEmployeesNumber(), getEmployeeRepository());
    }
}
