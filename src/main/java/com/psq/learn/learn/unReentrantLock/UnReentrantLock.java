package com.psq.learn.learn.unReentrantLock;

/**
 * 简单的不可重入锁
 */
public class UnReentrantLock {
    private boolean flag = false;
    public synchronized void lock() throws InterruptedException {
        System.out.println("进入lock()："+Thread.currentThread().getName());
        while (flag){
            System.out.println("进行等待："+Thread.currentThread().getName());
            wait();
        }
        //进行加锁
        flag = true;
    }

    public void unLock(){
        System.out.println("进入unLock解锁："+Thread.currentThread().getName());
        flag = false;
        //唤醒对象锁池里面的一个线程
        notify();
    }
}