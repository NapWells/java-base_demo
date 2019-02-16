package com.yyh.thead;

public class MyThread extends Thread{
    private String name;
    public MyThread(String name){this.name = name;}

    public void run() {
        int i = 0;
        while (true){
            if (isInterrupted()){
                System.out.println(name + " thread will be stop");
                break;
            }
            System.out.println("i = " + i);
            i ++;
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        System.out.println("stop");
    }
}
