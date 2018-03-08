package com.sptwin.sptserver.pattern.proxy.dynamicproxy.interceptor;

import com.sptwin.sptserver.pattern.proxy.dynamicproxy.jdkproxy.HelloWorld;
import com.sptwin.sptserver.pattern.proxy.dynamicproxy.jdkproxy.HelloWorldImpl;

public class Test {
    public static void main(String[] args) {
        HelloWorld helloWorld = (HelloWorld)InterceptorJdkProxy.bind(new HelloWorldImpl(),"com.sptwin.sptserver.pattern.proxy.dynamicproxy.interceptor.MyInterceptor");
        helloWorld.sayHellloWorld();
    }
}
