package com.psq.learn.learn.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 多线程调用
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("创建多线程方法：");
        //方法一：继承Thread
        ExtendsThread extendsThread = new ExtendsThread();
        extendsThread.setName("方法一：继承Thread");
        extendsThread.start();

        //方法二：实现Runnable
        Thread thread = new Thread(new ImplentsRunnable());
        thread.setName("方法二：实现Runnable");
        thread.start();

        //也可以转为lamb表达式
        Thread thread1 = new Thread(()->{
            System.out.println("线程名："+Thread.currentThread().getName());
        });
        thread1.setName("方法二的lamb：实现Runnable的lamb");
        thread1.start();

        FutureTask<Object> futureTask = new FutureTask<>(new ImplentsCallable());
        Thread thread2 = new Thread(futureTask);
        thread2.setName("方法三：实现Callable");
        thread2.start();

        FutureTask<Object> futureTask1 = new FutureTask(()->{
            System.out.println("线程名："+Thread.currentThread().getName());
            return "";
        });
        Thread thread3 = new Thread(futureTask1);
        thread3.setName("方法三的lamb：实现Callable的lamb");
        thread3.start();

        ExecutorService executor = Executors.newFixedThreadPool(3);
        System.out.println("方法四：线程池结合Runnable实现");
        for (int i=0; i<4; i++){
            executor.execute(new ThreadPool());
        }
        //用完线程池要关闭
        executor.shutdown();

        System.out.println("主线程名："+Thread.currentThread().getName());
    }
}