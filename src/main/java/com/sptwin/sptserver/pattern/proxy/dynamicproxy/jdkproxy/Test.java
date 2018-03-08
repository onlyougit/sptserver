package com.sptwin.sptserver.pattern.proxy.dynamicproxy.jdkproxy;

public class Test {
    public static void main(String[] args) {
        JdkProxy jdkProxy = new JdkProxy();
        HelloWorld proxy = (HelloWorld) jdkProxy.bind(new HelloWorldImpl());
        proxy.sayHellloWorld();
    }
}
