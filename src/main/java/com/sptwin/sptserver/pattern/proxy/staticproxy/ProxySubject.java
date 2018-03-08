package com.sptwin.sptserver.pattern.proxy.staticproxy;

public class ProxySubject implements Subject{

    private Subject subject;
    public ProxySubject(Subject subject){
        this.subject = subject;
    }
    @Override
    public void visit() {
        subject.visit();
    }
}
