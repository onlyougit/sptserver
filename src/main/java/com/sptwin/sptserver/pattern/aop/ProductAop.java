package com.sptwin.sptserver.pattern.aop;

import com.sptwin.sptserver.pattern.aop.service.AdminService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProductAop {
    @Autowired
    private AdminService adminService;

    @Pointcut("@annotation(com.sptwin.sptserver.pattern.aop.annotation.Authority)")
    public void adminOnly(){

    }
    @Before("adminOnly()")
    public void check(){
        adminService.check();
    }

}
