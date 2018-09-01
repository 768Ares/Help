package pl.storm;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Aspect
@Service
public class AspectDataBase {
//    @Autowired
//    private EntityManagerFactory entityManagerFactory;
//    @Autowired
//    private EntityManager entityManager;

    @Autowired
    EmployeeRepository employeeRepository;

    @Pointcut("execution(* pl.storm..*(..))")
    public void dataBasePointCut() {
    }

    @Pointcut("args(employee, ..)")
    public void employeArgAop(Employee employee) {
    }

    @Pointcut("args(businessPhone, ..)")
    public void buisnessPhoneArgAop(BusinessPhone businessPhone) {
    }

    @Around("employeArgAop(employee)")
    private void dataBaseConnection(ProceedingJoinPoint proceedingJoinPoint, Employee employee) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println(employee);
            Object[] args = new Object[]{employee, entityManager};
            proceedingJoinPoint.proceed(args);
            entityManager.getTransaction().commit();

            entityManager.close();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        entityManagerFactory.close();
    }

    @Around("buisnessPhoneArgAop(businessPhone)")
    private void dataBaseConnection(ProceedingJoinPoint proceedingJoinPoint, BusinessPhone businessPhone) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            System.out.println(businessPhone);
            Object[] args = new Object[]{businessPhone, entityManager};
            proceedingJoinPoint.proceed(args);
            entityManager.getTransaction().commit();

            entityManager.close();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        entityManagerFactory.close();
    }
}
