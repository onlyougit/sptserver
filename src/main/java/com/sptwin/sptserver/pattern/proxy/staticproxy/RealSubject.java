package com.sptwin.sptserver.pattern.proxy.staticproxy;

public class RealSubject implements Subject {

    private String content;

    public RealSubject(String content){
        this.content = content;
    }
    @Override
    public void visit() {
        System.out.println(content);
    }
}
