package com.sptwin.sptserver.pattern.proxy.dynamicproxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy implements InvocationHandler {

    private Object realObj = null;

    /**
     * 建立代理对象和真实对象的代理关系，并返回代理对象
     * @param realObj 软件工程师对象
     * @return 代理对象
     */
    public Object bind(Object realObj){
        this.realObj = realObj;
        //第二个参数：把生成的动态代理下挂在哪些接口下，这里就是放在realObj实现的接口下。
        return Proxy.newProxyInstance(realObj.getClass().getClassLoader(),realObj.getClass().getInterfaces(),this);
    }

    /**
     * 代理逻辑方法
     * @param proxy 商务对象
     * @param method 当前调度方法
     * @param args 当前方法参数
     * @return 代理结果返回
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("进入代理逻辑方法");
        System.out.println("在调度真实对象之前的服务");
        Object object = method.invoke(realObj,args);//相当于调用sayHellloWorld方法
        System.out.println("在调度真实对象之后的服务");
        return object;
    }
}
