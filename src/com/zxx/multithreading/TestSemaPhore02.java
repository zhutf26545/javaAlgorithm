package com.zxx.multithreading;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @Package: com.zxx.multithreading
 * @FileName: TeatRemaPhore02
 * @date 2021/5/26 21:20
 */
public class TestSemaPhore02 {

    static Semaphore semaphore = new Semaphore(10);

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    if (semaphore.availablePermits() == 0) {
                        System.out.println("车位不足，请稍后~");
                    }
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "进入停车场");
                    Thread.sleep(new Random().nextInt(10000));
                    System.out.println(Thread.currentThread().getName() + "离开停车场");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }

            }, i + "号车").start();
        }
    }
}
