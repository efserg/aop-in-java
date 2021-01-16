package ru.geekbrains.library.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import ru.geekbrains.library.annotation.Auth;
import ru.geekbrains.library.exception.InvalidUserException;
import ru.geekbrains.library.service.AuthService;

@Aspect
public class AnnotationAspectSample {

    @Pointcut("execution(* ru.geekbrains.library.service.BookCrudServiceImpl.*(..))")
    public void bookServiceMethods() {
    }

    @Pointcut("@annotation(ru.geekbrains.library.annotation.PerformanceTracing)")
    public void perfTracing() {
    }

    @Pointcut("@annotation(ru.geekbrains.library.annotation.Auth)")
    public void auth() {
    }

    @Before("auth()")
    public void checkAuth(JoinPoint joinPoint) {
        Auth annotation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(Auth.class);
        if (annotation != null &&
                annotation.role() != AuthService.getCurrentUser().getRole()) {
            throw new InvalidUserException();
        }
    }

    @Before("bookServiceMethods()")
    public void beforeAdviceExample(JoinPoint joinPoint) {
        System.out.println("BEFORE " + joinPoint.getSignature());
    }

    @After("bookServiceMethods()")
    public void afterAdviceExample(JoinPoint joinPoint) {
        System.out.println("AFTER " + joinPoint.getSignature());
    }

    @Around("perfTracing()")
    public Object aroundExample(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("Before method...");
        Object proceed = joinPoint.proceed();
        System.out.println("After method... " + (System.currentTimeMillis() - start) + " ms");
        return proceed;
    }

    @AfterReturning(value = "execution(* ru.geekbrains.library.service.BookCrudServiceImpl.get(String))", returning = "retVal")
    public void afterRetExample(Object retVal) {
        System.out.println("Return " + retVal);
    }

}
