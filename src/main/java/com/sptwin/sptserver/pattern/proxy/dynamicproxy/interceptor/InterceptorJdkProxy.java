package com.sptwin.sptserver.pattern.proxy.dynamicproxy.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InterceptorJdkProxy implements InvocationHandler {
    private Object target;
    private String className = null;//拦截器全限定名
    public InterceptorJdkProxy(Object target,String className){
        this.target = target;
        this.className = className;
    }

    /**
     * 绑定委托对象返回一个代理对象
     * @param target 真实对象
     * @param className
     * @return 代理对象
     */
    public static Object bind(Object target, String className){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),new InterceptorJdkProxy(target,className));
    }

    /**
     * 通过代理对象调用方法
     * @param proxy 代理对象
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(null == className){
            //没有设置拦截器则直接反射原有的方法
            return method.invoke(target,args);
        }
        Object result = null;
        //通过反射生成拦截器
        Interceptor interceptor = (Interceptor)Class.forName(className).newInstance();
        if(interceptor.before(proxy,target,method,args)){
            result = method.invoke(target, args);
        }else{
            interceptor.around(proxy,target,method,args);
        }
        interceptor.after(proxy,target,method,args);
        return result;
    }
}
