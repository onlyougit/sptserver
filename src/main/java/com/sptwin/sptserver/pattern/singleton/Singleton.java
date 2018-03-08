package com.sptwin.sptserver.pattern.singleton;

public class Singleton {
    private static volatile Singleton singleton;

    private Singleton(){}

    public static Singleton getInstant(){
        if(null == singleton){
            synchronized (Singleton.class){
                if(null == singleton){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
