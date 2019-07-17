package com.tw.apistackbase.Repository;

import com.tw.apistackbase.Class.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public interface CompanyRepository  extends JpaRepository<Company,Long> {
      List<Company> findAllDistinctByCompanyNameOrderByCompanyName(String companyName);

      @Transactional
      @Modifying
      @Query("update Company c set c.companyName = :companyName where c.companyID = :companyID")
      int updateCompanySetCompanyName(@Param("companyName")String companyName, @Param("companyID")Long companyID);
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
