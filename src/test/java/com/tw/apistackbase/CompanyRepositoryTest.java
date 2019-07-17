package com.tw.apistackbase;

import com.tw.apistackbase.Class.Company;
import com.tw.apistackbase.Repository.CompanyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Before
    public void setUp() {
        List<Company> companies = IntStream.rangeClosed(1, 2).boxed().map(x -> new Company()).collect(Collectors.toList());
        companyRepository.saveAll(companies);
        companyRepository.getOne(1L).setCompanyName("huawei");
        companyRepository.getOne(2L).setCompanyName("vivo");
    }

    @Test
    public void should_return_companies_when_find_all_distinct_by_company_name_order_by_companyName() {
        // given

        // when
        List<Company> foundCompanies = companyRepository.findAllDistinctByCompanyNameOrderByCompanyName("vivo");

        Assertions.assertEquals(1,foundCompanies.size());

        // then
        Assertions.assertEquals("vivo",foundCompanies.get(0).getCompanyName());

    }

    @Test
    public void should_update_company_when_update_its_name_by_id() {
        // given

        // when
        companyRepository.updateCompanySetCompanyName("xiaomi", 1L);

        // then
        Assertions.assertEquals("xiaomi",companyRepository.getOne(1L).getCompanyName());

    }
}
