package com.psq.learn.learn.Thread;

import java.util.concurrent.Callable;

/**
 * 多线程实现方法三：实现Callable接口，将对象传入FutureTask，再传入Thread中调用start()
 */
public class ImplentsCallable implements Callable<Object> {
    @Override
    public Object call() throws Exception {
        System.out.println("线程名："+Thread.currentThread().getName());
        return "";
    }
}