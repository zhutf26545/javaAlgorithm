package com.zxx.multithreading.JUC;

import java.util.concurrent.CountDownLatch;

/**
 * @Package: com.zxx.multithreading
 * @FileName: TestCountDownLatch01
 * @date 2021/5/26 11:27
 */
public class TestCountDownLatch01 {
    /**
     * 业务： 模拟主线程需要等待其他线程加载完才执行
     */
    // 展示页面基础数据
    public void showInfo() {
        System.out.println("showInfo");
    }

    // 展示页面基础数据
    public void check() {
        System.out.println("校验密令");
    }

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        TestCountDownLatch01 countDownLatch01 = new TestCountDownLatch01();
        new Thread(() -> {
            countDownLatch01.showInfo();
            countDownLatch.countDown();
        }).start();

        new Thread(() -> {
            countDownLatch01.check();
            countDownLatch.countDown();
        }).start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程run");
    }
}
