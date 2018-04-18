package com.sptwin.sptserver;

public class JavaTest {
    public static void main(String[] args) {
        FunInterface funInterface = ()-> System.out.println("Hello");
        funInterface.sayHello();
    }
}
