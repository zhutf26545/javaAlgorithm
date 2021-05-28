package com.zxx.multithreading.JUC;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Package: com.zxx.multithreading
 * @FileName: TestReadWriteLock
 * @date 2021/5/23 15:49
 */
public class TestReadWriteLock {
    /**
     * 读写锁
     * 读锁 共享锁
     * 写锁 排它锁
     */
    private static ReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        Lock readLock = lock.readLock();
        Lock writeLock = lock.writeLock();

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                try {
                    readLock.lock();
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("read ~");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    readLock.unlock();
                }
            }).start();
        }
        for (int j = 0; j < 2; j++) {
            new Thread(() -> {
                try {
                    writeLock.lock();
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("write ~");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    writeLock.unlock();
                }
            }).start();
        }
    }
}
