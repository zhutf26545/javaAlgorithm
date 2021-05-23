package com.zxx.multithreading;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Package: com.zxx.multithreading
 * @FileName: TestExchenger
 * @date 2021/5/23 16:01
 */
public class TestExchenger {
    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(() -> {
            String t1 = "t1";
            try {
                exchanger.exchange(t1, 10, TimeUnit.SECONDS);
                System.out.println(Thread.currentThread().getName() + " -- " + t1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }

        }, "T1").start();
        new Thread(() -> {
            String t2 = "t2";
            try {
                exchanger.exchange(t2);
                System.out.println(Thread.currentThread().getName() + " -- " + t2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "T2").start();

    }
}
