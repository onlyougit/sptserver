package com.sptwin.sptserver.pattern.proxy.dynamicproxy.blamechain;

import com.sptwin.sptserver.pattern.proxy.dynamicproxy.interceptor.InterceptorJdkProxy;
import com.sptwin.sptserver.pattern.proxy.dynamicproxy.jdkproxy.HelloWorld;
import com.sptwin.sptserver.pattern.proxy.dynamicproxy.jdkproxy.HelloWorldImpl;

public class Test {
    public static void main(String[] args) {
        HelloWorld proxy1 = (HelloWorld) InterceptorJdkProxy.bind(new HelloWorldImpl(),"com.sptwin.sptserver.pattern.proxy.dynamicproxy.blamechain.Interceptor1");
        HelloWorld proxy2 = (HelloWorld) InterceptorJdkProxy.bind(proxy1,"com.sptwin.sptserver.pattern.proxy.dynamicproxy.blamechain.Interceptor2");
        HelloWorld proxy3 = (HelloWorld) InterceptorJdkProxy.bind(proxy2,"com.sptwin.sptserver.pattern.proxy.dynamicproxy.blamechain.Interceptor3");
        proxy3.sayHellloWorld();
    }
}
