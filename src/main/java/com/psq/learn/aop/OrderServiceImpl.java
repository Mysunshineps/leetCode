package com.psq.learn.aop;

public class OrderServiceImpl implements OrderService{

    //生成订单号
    @Override
    public String addOrder(String orderInfo) {
        System.out.println("NO1");
        return "NO1";
    }

    //根据订单号查询订单信息
    @Override
    public String selectOrderInfo(String orderNo) {
        System.out.println("NO1订单信息");
        return "NO1订单信息";
    }
}