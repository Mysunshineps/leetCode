package com.psq.learn.aop;

public class ProxyMain {
    public static void main(String[] args){

        /**
         * 能否说下JDK动态代理和CGLib动态代理的区别
         * JDK动态代理，要求目标对象实现一个接口，但是有时候目标对象只是一个单独的对象,并没有实现任何的接口,这个时候就可以用CGLib动态代理
         * JDK动态代理是自带的，CGlib需要引入第三方包
         *
         * CGLib动态代理,它是在内存中构建一个子类对象从而实现对目标对象功能的扩展
         * CGLib动态代理基于继承来实现代理，所以无法对final类、private方法和static方法实现代理
         */
        //JDK动态代理
        JdkProxy jdkProxy = new JdkProxy();
        OrderService orderService = (OrderService) jdkProxy.newProxyInstance(new OrderServiceImpl());
        orderService.addOrder("NO1");
        orderService.selectOrderInfo("NO1");

        System.out.println();

        //CGLIB动态代理
        CgilbProxy cgilbProxy = new CgilbProxy();
        OrderService orderService1 = (OrderService) cgilbProxy.newProxyInstance(new OrderServiceImpl());
        orderService1.addOrder("NO1");
        orderService1.selectOrderInfo("NO1");
    }
}