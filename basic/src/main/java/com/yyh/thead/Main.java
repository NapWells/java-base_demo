package com.yyh.thead;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread myThread = new MyThread("A");
        myThread.start();
        System.out.println(myThread.getState().name());
        myThread.interrupt();
        Thread.sleep(3000);
        System.out.println(myThread.getState().name());


    }
}
