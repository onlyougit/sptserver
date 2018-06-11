package com.sptwin.sptserver.pattern.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopTest {

    @Pointcut("execution(* com.sptwin.sptserver.pattern.aop.service.impl.ProductServiceImpl.*(..))")
    public void test(){

    }
    /*@Before("test()")
    public void check1(){
        System.out.println("Before..............");
    }*/
    /*@After("test()")
    public void check2(){
        System.out.println("After..............");
    }*/
    /*@AfterReturning(value="test()",returning="result")
    public void check3(Object result){
        System.out.println("AfterReturning.............."+result);
    }*/
    /*@AfterThrowing("test()")
    public void check4(){
        System.out.println("AfterThrowing..............");
    }*/
    @Around("test()")
    public Object check5(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("相当于Before");
        Object obj= null;
        try {
            obj = proceedingJoinPoint.proceed();
            System.out.println("相当于AfterReturning");
        } catch (Throwable throwable) {
            System.out.println("相当于AfterThrowing");
            throwable.printStackTrace();
        } finally {
            System.out.println("相当于After");
        }
        return obj;
    }

}
