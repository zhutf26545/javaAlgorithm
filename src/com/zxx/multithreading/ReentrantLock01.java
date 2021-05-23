package com.zxx.multithreading;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Package: com.zxx.multithreading
 * @FileName: ReentrantLock01
 * @date 2021/5/23 11:57
 */
public class ReentrantLock01 {
    // boolean fair true：公平策略；默认是NonfairSync
//     Lock lock = new ReentrantLock(true);
    Lock lock = new ReentrantLock();

    /**
     * ReentrantLock（CAS） 可以代替 synchronized锁
     * 1、ReentrantLock（fair）  fair = true 公平策略；多个线程获取锁时，会先判断等待队列中有没有，有的话进入等待队列排序，（先到先到）
     * 2、ReentrantLock
     *      lock()
     *      tryLock() return：boolean 可以尝试获取锁，然后进行逻辑处理
     *      tryLock(long time, TimeUnit unit) 可以Thread.interrupt(),中断
     * 3、lockInterruptibly()  可中断的加锁，会抛出中断一场
     */
    void m1() {
        for (int i = 0; i < 10; i++) {
            try {
                lock.lock();
                TimeUnit.SECONDS.sleep(1);
                System.out.println("m1:" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    void m2() {
        Boolean lockStatus = false;
        try {
            try {
                lockStatus = lock.tryLock(3, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("I'm  m2");
        } finally {
            if (lockStatus) {
                lock.unlock();
            }
        }
    }

    void m3() {
        try {
            lock.lockInterruptibly();
            System.out.println("I'm  m3");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ReentrantLock01 s1 = new ReentrantLock01();
        new Thread(() -> s1.m1()).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> s1.m2()).start();

        Thread m3 = new Thread(() -> s1.m3());

        m3.start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m3.interrupt();
    }
}