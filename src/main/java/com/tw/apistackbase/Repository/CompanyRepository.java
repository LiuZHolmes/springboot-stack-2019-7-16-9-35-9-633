package com.tw.apistackbase.Repository;

import com.tw.apistackbase.Class.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CompanyRepository  extends JpaRepository<Company,Long> {

//    private List<Company> companies;
//
//    public CompanyRepository() {
//        this.companies = new ArrayList<>();
//    }
//
//    public void setCompanies(List<Company> companies) {
//        this.companies = companies;
//    }
//
//    public List<Company> getCompanies() {
//        return companies;
//    }
}
