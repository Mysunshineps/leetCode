package com.psq.learn.learn.thread;

/**
 * 多线程实现方法二：实现Runnable接口，将对象传入Thread，再调用start()
 */
public class ImplentsRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("线程名："+Thread.currentThread().getName());
    }
}