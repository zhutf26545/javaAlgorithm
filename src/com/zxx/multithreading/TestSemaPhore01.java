package com.zxx.multithreading;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @Package: com.zxx.multithreading
 * @FileName: TestSemaPhore01
 * @date 2021/5/26 21:16
 */
public class TestSemaPhore01 {
    static Semaphore semaphore = new Semaphore(10);
    public static void main(String[] args) {
        //模拟100辆车进入停车场
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    System.out.println("====" + Thread.currentThread().getName() + "来到停车场");
                    // 返回可用的令牌数量
                    if (semaphore.availablePermits() == 0) {
                        System.out.println("车位不足，请耐心等待");
                    }
                    semaphore.acquire();//获取令牌尝试进入停车场
                    System.out.println(Thread.currentThread().getName() + "成功进入停车场");
                    Thread.sleep(new Random().nextInt(10000));//模拟车辆在停车场停留的时间
                    System.out.println(Thread.currentThread().getName() + "驶出停车场");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();//释放令牌，腾出停车场车位
                }

            }, i + "号车").start();
        }
    }
}
