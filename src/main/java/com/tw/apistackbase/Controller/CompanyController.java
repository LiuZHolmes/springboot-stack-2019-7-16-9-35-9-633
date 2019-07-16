package com.tw.apistackbase.Controller;

import com.tw.apistackbase.Class.Company;
import com.tw.apistackbase.Exception.CompanyNotFoundException;
import com.tw.apistackbase.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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
}
