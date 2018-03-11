package com.sptwin.sptserver.pattern.reflect;

import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) {
        ReflectDemo reflectDemo = null;
        try {
            reflectDemo = (ReflectDemo) Class.forName("com.sptwin.sptserver.pattern.reflect.ReflectDemo").
                    getConstructor(String.class,Integer.class).newInstance("wang",27);
            Method method = reflectDemo.getClass().getMethod("sayHello");
            method.invoke(reflectDemo);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
