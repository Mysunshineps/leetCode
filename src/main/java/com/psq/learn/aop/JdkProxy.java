package com.psq.learn.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理
 */
public class JdkProxy implements InvocationHandler {

    //目标类
    private Object targetObject;

    //获取代理对象
    public Object newProxyInstance(Object targetObject){
        this.targetObject = targetObject;
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        Object result = null;
        try {
            System.out.println("通过JDK动态代理调用 " + method.getName() + "开始");
            result = method.invoke(targetObject, args);
            System.out.println("通过JDK动态代理调用 " + method.getName() + "结束");
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}