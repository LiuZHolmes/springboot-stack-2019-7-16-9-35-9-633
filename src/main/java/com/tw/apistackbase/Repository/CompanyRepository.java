package com.tw.apistackbase.Repository;

import com.tw.apistackbase.Class.Company;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyRepository {
    public CompanyRepository() {
        this.companies = new ArrayList<>();
    }

    private List<Company> companies;

    public List<Company> getCompanies() {
        return companies;
    }
}
