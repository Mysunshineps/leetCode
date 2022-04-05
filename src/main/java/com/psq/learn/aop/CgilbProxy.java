package com.psq.learn.aop;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB动态代理
 */
public class CgilbProxy implements MethodInterceptor {

    private Object targetObject;

    public Object newProxyInstance(Object target){
        this.targetObject = target;
        Enhancer enhancer = new Enhancer();
        //设置代理类的父类(目标类)
        enhancer.setSuperclass(this.targetObject.getClass());
        //设置回调函数
        enhancer.setCallback(this);
        //创建子类(代理对象)
        return enhancer.create();
    }



    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy){
        Object result = null;
        try{
            System.out.println("通过CGILB动态代理调用"+ method.getName() +"开始");
            result = methodProxy.invokeSuper(o,objects);
            System.out.println("通过CGILB动态代理调用"+ method.getName() +"结束");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }
}
