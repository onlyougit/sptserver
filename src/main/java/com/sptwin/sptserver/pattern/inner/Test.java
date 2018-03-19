package com.sptwin.sptserver.pattern.inner;

public class Test {
    public static void main(String[] args) {
        DemoService demoService = new DemoService() {
            @Override
            public void sayHello() {
                System.out.println("Hello World");
            }
        };
        demoService.sayHello();
        //下面是Lambda的方式
        DemoService demoService2 = ()-> System.out.println("Lambda");
        demoService2.sayHello();
    }
}
