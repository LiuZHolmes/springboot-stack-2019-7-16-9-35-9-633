package com.tw.apistackbase.Repository;

import com.tw.apistackbase.Class.Company;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyRepository {
    private List<Company> companies = new ArrayList<>();

    public List<Company> getCompanies() {
        return companies;
    }
}
