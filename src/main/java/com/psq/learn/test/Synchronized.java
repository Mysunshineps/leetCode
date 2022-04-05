package com.psq.learn.test;

import java.util.concurrent.locks.ReentrantLock;

public class Synchronized {

    public synchronized void lockA(){
        System.out.println("hello lockA");
    }

    private Integer num = 0;
    public void lockB(){
        ReentrantLock reentrantLock = new ReentrantLock();


        synchronized (num){
            System.out.println("hello lockB");
        }
    }

}