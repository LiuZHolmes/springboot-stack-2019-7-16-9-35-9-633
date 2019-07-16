package com.tw.apistackbase.Repository;

import com.tw.apistackbase.Class.Company;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyRepository {

    private List<Company> companies;

    public CompanyRepository() {
        this.companies = new ArrayList<>();
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public List<Company> getCompanies() {
        return companies;
    }
}
