package com.psq.learn.learn.deadLock;

/**
 * 手写死锁
 */
public class DeadLock {
    private static String a = "0";

    private static String b = "1";
    public void methodA(){
        synchronized (a){
            System.out.println("我是methodA中获取a的锁："+Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (b){
                System.out.println("我是methodA中获取b的锁："+Thread.currentThread().getName());
            }
        }
    }

    public void methodB(){
        System.out.println("我是methodB中获取b的锁："+Thread.currentThread().getName());
        synchronized (b){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (a){
                System.out.println("我是methodB中获取a的锁："+Thread.currentThread().getName());
            }
        }
    }
}