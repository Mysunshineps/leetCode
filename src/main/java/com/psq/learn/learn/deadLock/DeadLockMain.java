package com.psq.learn.learn.deadLock;

public class DeadLockMain {
    /**
     * 死锁的4个必要条件
     * ​
     * 互斥条件：资源不能共享，只能由一个线程使用
     * 请求与保持条件：线程已经获得一些资源，但因请求其他资源发生阻塞，对已经获得的资源保持不释放
     * 不可抢占：有些资源是不可强占的，当某个线程获得这个资源后，系统不能强行回收，只能由线程使用完自己释放
     * 循环等待条件：多个线程形成环形链，每个都占用对方申请的下个资源
     * ​
     * 只要发生死锁，上面的条件都成立；只要一个不满足，就不会发生死锁
     */
    public static void main(String[] args){

        DeadLock deadLock = new DeadLock();
        new Thread(()->{
            deadLock.methodA();
        }).start();
        new Thread(()->{
            deadLock.methodB();
        }).start();
        System.out.println("主线程："+ Thread.currentThread().getName());
    }
}
