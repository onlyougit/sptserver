package com.sptwin.sptserver.pattern.reflect;

public class ReflectDemo {
    private String name;
    private Integer age;
    public ReflectDemo(String name,Integer age){
        this.name = name;
        this.age = age;
    }
    public void sayHello(){
        System.out.println("姓名：" + name+"；年龄："+age);
    }
}
