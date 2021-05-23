package com.zxx.multithreading;

import java.util.concurrent.TimeUnit;

/**
 * @Package: com.zxx.multithreading
 * @FileName: SyncThread01
 * @date 2021/5/22 22:11
 */
public class SyncThread01 {

    /**
     * synchronized锁可以重入，一个类中多个方法加sync时，第一个调用者锁释放了其他线程才能才能获取。
     * 可以在锁方法中调用其他锁方法！
     */
    void m1() {
//        Object m1 = new Object();
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("m1: " + i);
                if (i == 2) {
                    m2();
                }
            }
        }
    }

    synchronized void m2() {
        System.out.println("I'm  m2");
    }

    public static void main(String[] args) {
        SyncThread01 s1 = new SyncThread01();
        new Thread(() -> s1.m1()).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> s1.m2()).start();

    }
}

