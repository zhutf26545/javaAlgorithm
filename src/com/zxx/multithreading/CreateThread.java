package com.zxx.multithreading;

/**
 * @Package: com.zxx.multithreading
 * @FileName: CreateThread
 * @date 2021/5/20 15:09
 */
public class CreateThread {

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("I'm Thread!");
        }
    }

    static class MyThreadR implements Runnable {

        @Override
        public void run() {
            System.out.println("I'm Thread(Runnable)!");
        }
    }

    public static void main(String[] args) {
        new MyThread().start();
        new Thread(new MyThreadR()).start();
        new Thread(() -> {
            System.out.println("I'm Lambda Thread!");
        }).start();
    }

    // 线程运行方式 1、Thread 2、Runable 3、lamdba 4、线程池Executors -> newCacheThread
}
