package com.tw.apistackbase.Controller;

import com.tw.apistackbase.Class.Company;
import com.tw.apistackbase.Class.Employee;
import com.tw.apistackbase.Exception.CompanyNotFoundException;
import com.tw.apistackbase.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/companies")
    public List<Company> listCompanies(@RequestParam(value = "page",defaultValue = "1") long page, @RequestParam(value = "pageSize", defaultValue = "100") long pageSize) {
        return companyRepository.findAll()
                .stream()
                .skip((page - 1) * pageSize)
                .limit(pageSize).collect(Collectors.toList());
    }

    @GetMapping("/companies/{companyID}")
    public Company findCompanyByID(@PathVariable long companyID) throws CompanyNotFoundException {
        Optional<Company> optionalCompany = companyRepository.findAll()
                .stream()
                .filter(x -> x.getCompanyID() == companyID)
                .findFirst();
        if (optionalCompany.isPresent()) return optionalCompany.get();
        else throw new CompanyNotFoundException();
    }

    @GetMapping("/companies/{companyID}/employees")
    public List<Employee> listCompanyEmployeesByCompanyID(@PathVariable long companyID) throws CompanyNotFoundException {
        Optional<Company> optionalCompany = companyRepository.findAll()
                .stream()
                .filter(x -> x.getCompanyID() == companyID)
                .findFirst();
        if (optionalCompany.isPresent()) return optionalCompany.get().getEmployeeRepository().getEmployees();
        else throw new CompanyNotFoundException();
    }

    @PostMapping("/companies")
    public ResponseEntity createCompany(@RequestBody Company company) {
        Company newCompany = companyRepository.save(company);
        return ResponseEntity.ok(newCompany);
    }

    @PutMapping("/companies/{companyID}")
    public Company updateCompany(@RequestBody Company newCompany, @PathVariable long companyID) throws CompanyNotFoundException {

        Optional<Company> optionalCompany = companyRepository.findAll()
                .stream()
                .filter(x -> x.getCompanyID() == companyID)
                .findFirst();
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            company.setEmployeeRepository(newCompany.getEmployeeRepository());
            company.setCompanyName(newCompany.getCompanyName());
            return company;
        } else throw new CompanyNotFoundException();
    }

    @DeleteMapping("/companies/{companyID}")
    public ResponseEntity deleteCompany(@PathVariable long companyID) {
          companyRepository.deleteById(companyID);
//        setCompanies(companyRepository.getCompanies()
//                .stream()
//                .filter(x -> x.getCompanyID() != companyID)
//                .collect(Collectors.toList())
//        );
        return ResponseEntity.ok().build();
    }
}
