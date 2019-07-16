package com.tw.apistackbase;


import com.tw.apistackbase.Controller.EmployeeController;
import com.tw.apistackbase.Repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    public void should_return_employee_list_when_get_employees() throws Exception {
        // given
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee();
        employee.setEmployeeID(1);
        employees.add(employee);
        when(employeeRepository.getEmployees()).thenReturn(employees);
        //when
        mockMvc.perform(get("/employees"))
                // then
                .andExpect(content().json(
                        "[{\"employeeID\":1}]"
                ));
    }

    @Test
    public void should_return_a_employee_when_get_a_employee_by_id() throws Exception {
        // given
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee();
        employee.setEmployeeID(1);
        employees.add(employee);
        when(employeeRepository.getEmployees()).thenReturn(employees);
        //when
        mockMvc.perform(get("/employees/1"))
                // then
                .andExpect(content().json(
                        "{\"employeeID\":1}"
                ));
    }

    @Test
    public void should_return_paged_employee_list_when_get_employees_page_by_page() throws Exception {
        // given
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee();
        employee.setEmployeeID(1);
        employees.add(employee);
        when(employeeRepository.getEmployees()).thenReturn(employees);
        //when
        mockMvc.perform(get("/employees?page=1&pageSize=5"))
                // then
                .andExpect(content().json(
                        "[{\"employeeID\":1}]"
                ));
    }

    @Test
    public void should_return_male_employees_when_get_employees_by_gender() throws Exception {
        // given
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee();
        employee.setEmployeeID(1);
        employee.setGender("male");
        employees.add(employee);
        when(employeeRepository.getEmployees()).thenReturn(employees);
        //when
        mockMvc.perform(get("/employees?gender=male"))
                // then
                .andExpect(content().json(
                        "[{\"employeeID\":1}]"
                ));
    }
}
