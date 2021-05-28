package com.zxx.multithreading.JUC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Package: com.zxx.multithreading
 * @FileName: ReentrantLock02
 * @date 2021/5/26 9:32
 */
public class ReentrantLock02 {
    /**
     * 业务： 模拟三个线程，线程1输出一次A后线程2输出两次B后线程3输出3次C，换行。依次执行5次
     */
    private static int num = 1;
    private static Lock reentrantLock = new ReentrantLock();
    private static Condition condition1 = reentrantLock.newCondition();
    private static Condition condition2 = reentrantLock.newCondition();
    private static Condition condition3 = reentrantLock.newCondition();

    public static void t1() throws InterruptedException {
        reentrantLock.lock();
        try {
            while (num != 1) {
                condition1.await();
            }
            System.out.print("A ");
            num = 2;
            condition2.signal();
        } finally {
            reentrantLock.unlock();
        }
    }

    public static void t2() throws InterruptedException {
        reentrantLock.lock();
        try {
            while (num != 2) {
                condition2.await();
            }
            System.out.print("B-B ");
            num = 3;
            condition3.signal();
        } finally {
            reentrantLock.unlock();
        }
    }

    public static void t3() throws InterruptedException {
        reentrantLock.lock();
        try {
            while (num != 3) {
                condition3.await();
            }
            System.out.print("C-C-C");
            System.out.println();
            num = 1;
            condition1.signal();
        } finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    ReentrantLock02.t1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    ReentrantLock02.t2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    ReentrantLock02.t3();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
