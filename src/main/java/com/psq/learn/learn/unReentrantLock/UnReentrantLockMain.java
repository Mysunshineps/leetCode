package com.psq.learn.learn.unReentrantLock;

public class UnReentrantLockMain {
    public static void main(String[] args){
        new UnReentrantLockMain().methodA();
    }

    private UnReentrantLock unReentrantLock = new UnReentrantLock();
    public void methodA(){
        try {
            unReentrantLock.lock();
            System.out.println("methodA被调用");
            methodB();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            unReentrantLock.unLock();
        }
    }

    public void methodB(){
        try {
            unReentrantLock.lock();
            System.out.println("methodB被调用");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            unReentrantLock.unLock();
        }
    }
}