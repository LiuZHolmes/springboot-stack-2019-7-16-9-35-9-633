package com.tw.apistackbase.Controller;

import com.tw.apistackbase.Class.Company;
import com.tw.apistackbase.Employee;
import com.tw.apistackbase.Exception.CompanyNotFoundException;
import com.tw.apistackbase.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.jvm.hotspot.debugger.Page;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/companies")
    public List<Company> listCompanies() {
        return companyRepository.getCompanies();
    }

    @GetMapping("/companies/{companyID}")
    public Company findCompanyByID(@PathVariable long companyID) throws CompanyNotFoundException {
        Optional<Company> optionalCompany = companyRepository.getCompanies()
                .stream()
                .filter(x -> x.getCompanyID() == companyID)
                .findFirst();
        if (optionalCompany.isPresent()) return optionalCompany.get();
        else throw new CompanyNotFoundException();
    }

    @GetMapping("/companies/{companyID}/employees")
    public List<Employee> listCompanyEmployeesByCompanyID(@PathVariable long companyID) throws CompanyNotFoundException {
        Optional<Company> optionalCompany = companyRepository.getCompanies()
                .stream()
                .filter(x -> x.getCompanyID() == companyID)
                .findFirst();
        if (optionalCompany.isPresent()) return optionalCompany.get().getEmployeeRepository().getEmployees();
        else throw new CompanyNotFoundException();
    }

    @GetMapping("/companies?page={page}&pageSize={pageSize}")
    public List<Company> listCompaniesPageByPage(@PathVariable int page, @PathVariable int size) {
        return companyRepository.getCompanies()
                .stream()
                .skip((page - 1) * size)
                .limit(size).collect(Collectors.toList());
    }

    @PostMapping("/companies")
    public Company createCompany(@RequestBody String request) {
        Company company = new Company();
        company.setCompanyID(1);
        companyRepository.getCompanies().add(company);
        return company;
    }

    @PutMapping("/companies/{companyID}")
    public Company updateCompany(@RequestBody Company newCompany, @PathVariable long companyID) throws CompanyNotFoundException {

        Optional<Company> optionalCompany = companyRepository.getCompanies()
                .stream()
                .filter(x -> x.getCompanyID() == companyID)
                .findFirst();
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            company.setEmployeeRepository(newCompany.getEmployeeRepository());
            company.setName(newCompany.getName());
            return company;
        } else throw new CompanyNotFoundException();
    }
}
