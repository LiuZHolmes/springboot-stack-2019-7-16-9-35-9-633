package com.tw.apistackbase;

import com.tw.apistackbase.Class.Company;
import com.tw.apistackbase.Repository.CompanyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CompanyTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyRepository companyRepository;

    @Test
    public void should_return_company_list_when_get_companies() throws Exception {
        // given
        List<Company> companies = new ArrayList<>();
        Company company = new Company();
        company.setCompanyID(1);
        companies.add(company);
        when(companyRepository.getCompanies()).thenReturn(companies);
        //when
        mockMvc.perform(get("/companies"))
                // then
                .andExpect(content().json(
                        "[{id:1}]"
                ));
    }

}
