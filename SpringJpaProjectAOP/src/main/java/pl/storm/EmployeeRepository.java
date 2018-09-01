package pl.storm;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Repository
public class EmployeeRepository {

    public Employee findById(Long id, EntityManager entityManager) {
        Employee employee = entityManager.find(Employee.class, id);
        return employee;
    }

    public Collection<Employee> findAllEmployee(EntityManager entityManager) {
        Query query = entityManager.createQuery("SELECT e FROM Employee  e order by id");
        return (Collection<Employee>) query.getResultList();
    }

    public Iterator findAllEmployeeThanHigerSalary(EntityManager entityManager, Double salary) {
        Query query = entityManager.createQuery("SELECT e.firstName, e.lastName from Employee e where salary > :employeeSalary");
        query.setParameter("employeeSalary", salary);
        return query.getResultList().iterator();
    }

    public Collection<Employee> findMoreckiAndPawlak(EntityManager entityManager, List<String> surnames) {
        Query query = entityManager.createQuery("SELECT e from Employee e where lastName in :surnamesEmployee");
        query.setParameter("surnamesEmployee", surnames);
        return query.getResultList();
    }

    public Employee add(Employee employee, EntityManager entityManager) {
        entityManager.persist(employee);
        return employee;
    }
    public Employee update(Employee employee, EntityManager entityManager) {
        entityManager.merge(employee);
        return employee;
    }

    public Employee delete(Employee employee, EntityManager entityManager) {
        entityManager.remove(employee);
        return employee;
    }
}
