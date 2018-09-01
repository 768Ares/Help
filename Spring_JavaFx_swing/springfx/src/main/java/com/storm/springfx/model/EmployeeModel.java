package com.storm.springfx.model;

import com.storm.springfx.dao.Employee;
import com.storm.springfx.dao.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;

@Component
public class EmployeeModel {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeModel(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostConstruct
    public void runAtStart() {
        List<Employee> employees = (List<Employee>) employeeRepository.findAll();
        for (Employee employee : employees) {
            System.out.println(employee);
        }

    }
}
