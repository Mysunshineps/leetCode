package com.psq.learn.learn.Thread;

/**
 * 多线程实现方法一：继承Thread，然后直接调用start方法
 */
public class ExtendsThread extends Thread {
    @Override
    public void run() {
        System.out.println("线程名："+Thread.currentThread().getName());
    }
}