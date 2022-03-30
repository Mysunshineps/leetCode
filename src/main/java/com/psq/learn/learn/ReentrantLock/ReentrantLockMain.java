package com.psq.learn.learn.ReentrantLock;

public class ReentrantLockMain {
    public static void main(String[] args){
        for (int i=0; i<10; i++){
            new ReentrantLockMain().methodA();
        }
    }

    private ReentrantLock reentrantLock = new ReentrantLock();
    private void methodA(){
        try {
            reentrantLock.lock();
            System.out.println("methodA被调用");
            methodB();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            reentrantLock.unLock();
        }
    }

    private void methodB(){
        try {
            reentrantLock.lock();
            System.out.println("methodB被调用");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            reentrantLock.unLock();
        }
    }
}