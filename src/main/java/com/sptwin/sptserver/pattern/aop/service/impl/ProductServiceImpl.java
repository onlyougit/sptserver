package com.sptwin.sptserver.pattern.aop.service.impl;

import com.sptwin.sptserver.pattern.aop.annotation.Authority;
import com.sptwin.sptserver.pattern.aop.service.ProductService;
import org.springframework.stereotype.Service;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Authority
    @Override
    public void delete() {
        System.out.println("删除商品");
    }

    @Override
    public void test1() {
        System.out.println("test1");
    }

    @Override
    public String test2() {
        System.out.println("test2");
        return "hello";
    }

    @Override
    public void test3(String name) {
        System.out.println("test3");
    }

    @Override
    public void test4() {
        System.out.println("test4");
        System.out.println(0/0);
    }

}
