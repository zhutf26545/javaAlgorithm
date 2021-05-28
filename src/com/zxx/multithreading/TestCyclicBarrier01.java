package com.zxx.multithreading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Package: com.zxx.multithreading
 * @FileName: TestCyclicBarrier01
 * @date 2021/5/26 14:32
 */
public class TestCyclicBarrier01 {
    /**
     * cyclicBarrier 循环栅栏
     * 实例： 多个线程求和，最后汇总
     */
    AtomicInteger atomicInteger = new AtomicInteger(0);
    CyclicBarrier cyclicBarrier = new CyclicBarrier(20, () -> {
        System.out.println("最总计算值：" + atomicInteger.get());
    });

    public void getCount() {
        try {
            for (int j = 0; j < 10000; j++) {
                atomicInteger.incrementAndGet();
            }
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TestCyclicBarrier01 cyclicBarrier01 = new TestCyclicBarrier01();
        /**
         * cyclicBarrier 是一组数据等待，最后完成；可以重复执行，对主线程不影响
         */
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                cyclicBarrier01.getCount();
            }).start();
        }
        System.out.println("11111");
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                cyclicBarrier01.getCount();
            }).start();
        }
        System.out.println("22222");
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                cyclicBarrier01.getCount();
            }).start();
        }
        System.out.println("33333");

    }
}
