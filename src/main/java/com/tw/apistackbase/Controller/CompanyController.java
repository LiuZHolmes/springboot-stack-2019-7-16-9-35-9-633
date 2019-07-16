package com.tw.apistackbase.Controller;

import com.tw.apistackbase.Class.Company;
import com.tw.apistackbase.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/companies")
    public List<Company> listCompanies() {
        return companyRepository.getCompanies();
    }
}
