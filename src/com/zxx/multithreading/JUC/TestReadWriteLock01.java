package com.zxx.multithreading.JUC;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Package: com.zxx.multithreading
 * @FileName: TestReadWriteLock01
 * @date 2021/5/26 20:40
 */
public class TestReadWriteLock01 {

    public static void main(String[] args) throws InterruptedException {

        final ReadWriteLock lock = new ReentrantReadWriteLock();
        lock.writeLock().lock();
        System.out.println("write lock");
        lock.readLock().lock();
        System.out.println("read lock");

    }

    public void m1() throws InterruptedException {
        final ReadWriteLock lock = new ReentrantReadWriteLock();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.writeLock().lock();
                System.out.println("Thread real execute");
                lock.writeLock().unlock();
            }
        });

        lock.writeLock().lock();
        t.start();
        Thread.sleep(200);

        System.out.println("realse one once");
        lock.writeLock().unlock();
    }
}
