package com.tw.apistackbase;

import com.tw.apistackbase.Class.Company;
import com.tw.apistackbase.Class.Employee;
import com.tw.apistackbase.Repository.CompanyRepository;
import com.tw.apistackbase.Repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CompanyTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyRepository companyRepository;


    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    public void should_return_company_list_when_get_companies() throws Exception {
        // given
        List<Company> companies = new ArrayList<>();
        Company company = new Company();
        company.setCompanyID(1);
        companies.add(company);
        when(companyRepository.findAll()).thenReturn(companies);
        //when
        mockMvc.perform(get("/companies"))
                // then
                .andExpect(content().json(
                        "[{\"companyID\":1}]"
                ));
    }

    @Test
    public void should_return_a_company_when_get_a_company_by_id() throws Exception {
        // given
        List<Company> companies = new ArrayList<>();
        Company company = new Company();
        company.setCompanyID(1);
        companies.add(company);
        when(companyRepository.findAll()).thenReturn(companies);
        //when
        mockMvc.perform(get("/companies/1"))
                // then
                .andExpect(content().json(
                        "{\"companyID\":1}"
                ));
    }

    @Test
    public void should_return_employees_list_when_get_company_employees() throws Exception {
        // given
        List<Company> companies = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        Company company = new Company();
        Employee employee = new Employee();
        company.setCompanyID(1);
        employee.setEmployeeID(1);
        company.setEmployeeRepository(employeeRepository);
        companies.add(company);
        employees.add(employee);

        when(companyRepository.findAll()).thenReturn(companies);
        when(employeeRepository.getEmployees()).thenReturn(employees);
        //when
        mockMvc.perform(get("/companies/1/employees"))
                // then
                .andExpect(content().json(
                        "[{\"employeeID\":1}]"
                ));
    }

    @Test
    public void should_return_paged_company_list_when_get_companies_page_by_page() throws Exception {
        // given
        List<Company> companies = IntStream.rangeClosed(1,7).boxed().map(Company::new).collect(Collectors.toList());
        when(companyRepository.findAll()).thenReturn(companies);
        //when
        mockMvc.perform(get("/companies?page=1&pageSize=5"))
                // then
                .andExpect(content().json(
                        "[" +
                                "{\"companyID\":1}," +
                                "{\"companyID\":2}," +
                                "{\"companyID\":3}," +
                                "{\"companyID\":4}," +
                                "{\"companyID\":5}" +
                                "]"
                ));
    }

    @Test
    public void should_return_a_company_when_post_a_company() throws Exception {
        // given
        Company company = new Company();
        company.setCompanyName("huawei");
        when(companyRepository.save(company)).thenReturn(company);
        //when
        mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"companyName\":\"huawei\"}"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName").value("huawei"));
    }

    @Test
    public void should_return_a_company_when_update_it() throws Exception {
        // given
        List<Company> companies = new ArrayList<>();
        Company company = new Company();
        company.setCompanyID(1);
        companies.add(company);
        when(companyRepository.findAll()).thenReturn(companies);
        //when
        mockMvc.perform(put("/companies/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                "\t\"companyName\" : \"huawei\"\n" +
                "}"))
                // then
                .andExpect(jsonPath("$.companyID").value("1"))
                .andExpect(jsonPath("$.companyName").value("huawei")
                );
    }

    @Test
    public void should_delete_a_company_when_delete_it() throws Exception {
        // given
        List<Company> companies = new ArrayList<>();
        Company company = new Company();
        company.setCompanyID(1);
        companies.add(company);
        when(companyRepository.findAll()).thenReturn(companies);
        //when
        mockMvc.perform(delete("/companies/1"))
                // then
                .andExpect(status().isOk());
    }
}
