package com.storm.firstaplicationspringmvc;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Order()
@Component
@RequestMapping(value = "employee", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController()
public class RunAtStart {
    private final EmployeeRepository employeeRepository;
    private final Logger logger = Logger.getLogger(RunAtStart.class);

    @Autowired
    public RunAtStart(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostConstruct
    public void runAtStart() {
        List<Employee> employees = (List<Employee>) employeeRepository.findAll();
//        printAll(employees);
        Employee employee = new Employee();
        employee.setFirstName("ala");
        employee.setLastName("kotamaha");
        employee.setSalary(new BigDecimal("9999.9"));

        employees.add(employee);



    }

    @RequestMapping(method = RequestMethod.POST)
    public Employee addEmployee(@RequestBody Employee employee){
        employeeRepository.save(employee);
        return employee;
    }

    @GetMapping
    List<Employee> findAll() {return (List<Employee>) employeeRepository.findAll();}

    @GetMapping(value = "/{id:\\d*}")
    ResponseEntity<Optional<Employee>> findById(@PathVariable("id") Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        Optional empty = Optional.empty();
        if(employee != empty ){
            return ResponseEntity.ok(employee);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{firstName:[a-zA-Z]+}")
    HttpEntity<List<Employee>> findOne(@PathVariable("firstName") String name) {
        List<Employee> employee = employeeRepository.findByFirstName(name);
        if(employee.size() > 0) {
            return ResponseEntity.ok(employee);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    Employee addEmployee(
            @RequestParam(value = "firstName", required = true) String firstName,
            @RequestParam(value = "lastName", required = true) String lastName,
            @RequestParam(required = true)BigDecimal sallary
            ) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setSalary(sallary);
        employee.setEmploymentDate(LocalDate.now());
        employeeRepository.save(employee);
        return employee;
    }

    @RequestMapping(value = "/{id:\\d*}", method = RequestMethod.PUT)
    ResponseEntity<Employee> updateEmployeeName(
            @PathVariable("id") Long id,
            @RequestHeader(value = "firstName", required = true) String name) {
        Optional<Employee> employee = employeeRepository.findById(id);
        Optional empty = Optional.empty();
        if(employee != empty ){
            Employee employeeObj = employee.get();
            System.out.println(employeeObj);
            employeeObj.setFirstName(name);
            employeeRepository.save(employeeObj);
            return ResponseEntity.ok(employeeObj);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private void printAll(List<Employee> allemployees) {
        for (Employee allemployee : allemployees) {
            System.out.println(allemployee);
        }
    }
}
