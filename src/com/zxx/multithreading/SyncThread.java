package com.zxx.multithreading;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Package: com.zxx.multithreading
 * @FileName: SyncThread
 * @date 2021/5/20 17:46
 */
public class SyncThread {

    /**
     * TODO:
     * 如果synchronized锁的方法中有报错就会释放锁，由其他线程获取锁接着处理！
     * synchronized（Object），不能是String,Integer.long
     * Synchronized 锁升级， 偏移锁，自旋锁，重量级锁；
     * CAS (compare and swap)  ABA问题，版本version
     * volatile 线程之间可见性；禁止命令重排序，不能保证原子性，不能替代synchronized
     * AtomicInteger CAS (unsafe)
     */

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

        new Thread(r, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r, "t2").start();
    }
}
