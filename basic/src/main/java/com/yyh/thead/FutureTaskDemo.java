package com.yyh.thead;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        FutureTask<String> futureTask = new FutureTask<String>(new MyCallable());
        Thread thread = new Thread(futureTask);
        thread.start();
        if (!futureTask.isDone()){
            System.out.println("task has not finished, please wait!");
        }
        System.out.println("task return : " + futureTask.get(10, TimeUnit.SECONDS));
    }
}
