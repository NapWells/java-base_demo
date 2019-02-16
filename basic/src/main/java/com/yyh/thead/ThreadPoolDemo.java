package com.yyh.thead;

import java.util.concurrent.*;

public class ThreadPoolDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future =  executorService.submit(new MyCallable());
        if (!future.isDone()){
            System.out.println("task not done");
        }
        System.out.println(future.get());
    }
}
