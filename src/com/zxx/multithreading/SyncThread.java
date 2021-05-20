package com.zxx.multithreading;

import java.util.concurrent.TimeUnit;

/**
 * @Package: com.zxx.multithreading
 * @FileName: SyncThread
 * @date 2021/5/20 17:46
 */
public class SyncThread {

    static class T {
        int count = 0;
        public synchronized void m() {
            System.out.println("m start!");

            while (1 == 1) {
                count++;
                System.out.println(Thread.currentThread().getName() + " count :" + count);

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (count == 5) {
                    int i = 1 / 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        T t = new T();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                t.m();
            }
        };

        new Thread( r, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread( r, "t2").start();
    }
}
