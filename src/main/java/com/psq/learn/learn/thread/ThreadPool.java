package com.psq.learn.learn.thread;

/**
 * 多线程实现方法四：线程池结合Runnable实现，实现Runnable接口再将对象传入线程池执行
 */
public class ThreadPool implements Runnable {
    @Override
    public void run() {
        System.out.println("线程名："+ Thread.currentThread().getName());
    }
}