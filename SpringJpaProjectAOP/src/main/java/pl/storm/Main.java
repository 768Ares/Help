package pl.storm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class Main {


    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        EmployeeRepository employeeRepository = context.getBean("employeeRepository", EmployeeRepository.class);
        BuisnessPhoneRepository buisnessPhoneRepository = context.getBean("buisnessPhoneRepository", BuisnessPhoneRepository.class);

        Employee employee = employeeRepository.findById(1L, entityManager);
        employee.setSalary(4532.01);
        employeeRepository.update(employee, entityManager);

        Employee employee2 = new Employee();
        BusinessPhone businessPhone = new BusinessPhone();
        employee2.setFirstName("Damian");
        employee2.setLastName("Morecki");
        employee2.setSalary(3543.32);
        businessPhone.setNumber(321456321);
        employee2.setBusinessPhone(businessPhone);
        buisnessPhoneRepository.add(businessPhone, entityManager);
        employeeRepository.add(employee2, entityManager);

        Collection<Employee> allEmployee = employeeRepository.findAllEmployee(entityManager);
        for (Employee employeeRow : allEmployee) {
            System.out.println(employeeRow);
        }

        Iterator allEmployeeThanHigerSalary = employeeRepository.findAllEmployeeThanHigerSalary(entityManager, 3000.00);

        while (allEmployeeThanHigerSalary.hasNext()) {
            Object[] item = (Object[])allEmployeeThanHigerSalary.next();
            String name = (String)item[0];
            String surname = (String)item[1];

            System.out.println(name + " " + surname);
        }

        ArrayList<String> surnamesList = new ArrayList<String>();
        surnamesList.add("Pawlak");
        surnamesList.add("Morecki");

        Collection<Employee> moreckiAndPawlak = employeeRepository.findMoreckiAndPawlak(entityManager, surnamesList);
        for (Employee iterMoreckiAndPawlak : moreckiAndPawlak) {
            System.out.println(iterMoreckiAndPawlak);
        }
        entityManagerFactory.close();
    }
}
