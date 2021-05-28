package com.zxx.multithreading.JUC;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @Package: com.zxx.multithreading
 * @FileName: TestCountDownLatch02
 * @date 2021/5/26 12:27
 */
public class TestCountDownLatch02 {
    /**
     * 业务： 模拟汽车站发车， 人满了或者到时间了就发车
     * CountDownLatch 和 CyclicBraiier 区别
     * 都可以实现线程之间等待
     * 1、CountDownLatch 实现一个或多个线程等待； 一个线程等待其他线程执行了在执行，类似于主页面加载； 多个线程等待，类似于比赛长跑
     *    CyclicBraiier 实现的是一组数据的等待，类似于多个线程求和，最总等待那个汇总。 调用的线程可以做其他事。
     * 2、CountDownLatch是减计数，计数减为0后不能重用；而CyclicBarrier是加计数，可置0后复用
     */
    public static void main(String[] args) {
        // 假定一个车可以坐20人
        CountDownLatch countDownLatch = new CountDownLatch(1);
        // 模拟10秒后发车，不管人满没满
        int second = 10;

        new Thread(() -> {
            for (int i = 1; i <= 20; i++) {
                System.out.println("第" + i + "人来了~");
                Random r = new Random();
                try {
                    TimeUnit.SECONDS.sleep(r.nextInt(3));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (i == 20) {
                    countDownLatch.countDown();
                    System.out.println("人满了");
                }
            }
        }).start();

        try {
            countDownLatch.await(second, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main.stat~ 发车了");


    }
}
