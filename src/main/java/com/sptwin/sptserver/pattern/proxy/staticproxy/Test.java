package com.sptwin.sptserver.pattern.proxy.staticproxy;

import java.util.TimeZone;

public class Test {
    public static void main(String[] args) {
        /*ProxySubject proxySubject = new ProxySubject(new RealSubject("静态代理测试"));
        proxySubject.visit();*/
        String[] ids = TimeZone.getAvailableIDs();
        for (String id:ids)
            System.out.printf(id+", ");
    }
}
