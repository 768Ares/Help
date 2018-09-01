package info.owczarek.springtutorial;


import info.owczarek.springtutorial.domain.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class AspectClass {

    @Pointcut("execution(* info.owczarek.springtutorial..*(..))")
    public void allPoincut() {
    }

    @Pointcut("execution(* info.owczarek.springtutorial..crash*(..))")
    public void exceptionPoincut() {
    }

    @Pointcut("args(arg)")
    public void OneArg(String arg) {
    }

    @Pointcut(value = "@annotation(info.owczarek.springtutorial.TestAnnotation)")
    public void annotationPointcut() {
    }

    @Pointcut("@target(info.owczarek.springtutorial.TestTargetAnnotation)")
    public void annotationTargetPointcut() {
    }

    @Before("allPoincut()")
    private void allBefore(JoinPoint joinPoint) {
        System.out.println("@Before - " + joinPoint.getSignature().getName());
    }

    @After("allPoincut()")
    private void allAfter(JoinPoint joinPoint) {
        System.out.println("@After - " + joinPoint.getSignature().getName());
    }

    @AfterReturning("OneArg(arg)")
    private void allAfterReturningOneString(String arg) {
        System.out.println("@AfterReturning - arg: " + arg);
    }

    @AfterReturning(value = "annotationPointcut()", returning = "name")
    private void annotationAfterReturning(JoinPoint joinPoint, User name) {
        System.out.println("###annotationAfterReturning , user name: " + name.getName() + " *- " + joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "annotationTargetPointcut()")
    private void annotationTagetAfterReturning(JoinPoint joinPoint) {
        System.out.println("###annotationTagetAfterReturning - " + joinPoint.getSignature().getName());
    }

    @AfterThrowing(value = "exceptionPoincut()", throwing = "ex")
    private void allAfterThrowingOneString(RuntimeException ex) {
        System.out.println("^^^^^^^^^^^^@AfterThrowing - arg: " + ex);

    }

    @Around("exceptionPoincut()")
    private void Around(ProceedingJoinPoint proceedingJoinPoint) {
        long startTime = System.nanoTime();
        try {
            proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {}
        long endTime = System.nanoTime();

        System.out.println("%%% Around method time in ns : " + (endTime - startTime));

    }
}
