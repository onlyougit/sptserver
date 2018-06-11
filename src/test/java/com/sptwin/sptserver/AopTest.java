package com.sptwin.sptserver;

import com.sptwin.sptserver.pattern.aop.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AopTest {
    @Autowired
    private ProductService productService;

    @Test
    public void testOne(){
        productService.delete();
    }
    @Test
    public void test(){
        productService.test1();
        productService.test2();
        productService.test3("world");
        productService.test4();
    }
}
