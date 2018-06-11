package com.sptwin.sptserver.pattern.aop.service.impl;

import com.sptwin.sptserver.pattern.aop.service.AdminService;
import com.sptwin.sptserver.pattern.aop.service.ProductService;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Override
    public void check() {
        System.out.println("权限校验");
    }
}
