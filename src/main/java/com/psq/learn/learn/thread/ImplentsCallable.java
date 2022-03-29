package com.psq.learn.learn.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程实现方法三：实现Callable接口，将对象传入FutureTask，再传入Thread中调用start()
 */
public class ImplentsCallable implements Callable<Object> {
    @Override
    public Object call() throws Exception {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        atomicInteger.getAndIncrement();
        List<Object> objects = Collections.synchronizedList(new ArrayList<>());
        System.out.println("线程名："+Thread.currentThread().getName());
        return "";
    }
}