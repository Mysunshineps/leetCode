package com.psq.learn.learn.ReentrantLock;

/**
 * 可重入锁
 */
public class ReentrantLock {
    private boolean flag = false;
    //用于记录是不是重入的线程
    private Thread currentThread = null;
    //累计加锁次数，加锁一次加1，减锁一次减1
    private int lockedCount = 0;

    public synchronized void lock() throws InterruptedException {
        Thread thread = Thread.currentThread();
        System.out.println("进入lock()："+thread.getName());
        //判断是否是同个线程获取锁，引用地址的比较
        while (flag && this.currentThread != thread){
            System.out.println("等待："+Thread.currentThread().getName());
            System.out.println("当前锁状态："+flag);
            System.out.println("当前加锁次数："+lockedCount);
            wait();
        }
        flag = true;
        currentThread = thread;
        lockedCount++;
    }

    public synchronized void unLock(){
        Thread thread = Thread.currentThread();
        System.out.println("进入unLock()："+thread.getName());
        if (this.currentThread == thread){
            lockedCount--;
            if(lockedCount == 0){
                flag = false;
                currentThread = null;
                notify();
            }
        }
    }
}