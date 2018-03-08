package com.sptwin.sptserver.pattern.proxy.dynamicproxy.cglibproxy;

public class Test {
    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        HelloImpl cglib = (HelloImpl) cglibProxy.getProxy(HelloImpl.class);
        cglib.sayHello("zhang");
    }
}
