package com.yyh.thead;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {
    public String call() throws Exception {
        String value = "test";
        System.out.println("Ready to work");
        Thread.sleep(5000);
        System.out.println("task done");
        return value;
    }
}
