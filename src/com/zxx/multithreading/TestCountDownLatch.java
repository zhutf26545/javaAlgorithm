package com.zxx.multithreading;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Package: com.zxx.multithreading
 * @FileName: TestCountDownLatch
 * @date 2021/5/23 12:50
 */
public class TestCountDownLatch {

    private static List<Thread> listThread = new ArrayList<>(100);
    /**
     * TODO
     * 用法一： 某一线程在开始运行前等待n个线程执行完毕
     *      将CountDownLatch的计数器初始化为n new CountDownLatch(n) ，
     *      每当一个任务线程执行完毕，就将计数器减1 countdownlatch.countDown()，
     *      当计数器的值变为0时，在CountDownLatch上 await() 的线程就会被唤醒。
     *      一个典型应用场景就是启动一个服务时，主线程需要等待多个组件加载完毕，之后再继续执行。
     * 用法二： 实现多个线程开始执行任务的最大并行性。
     *      注意是并行性，不是并发，强调的是多个线程在某一时刻同时开始执行。
     *      类似于赛跑，将多个线程放到起点，等待发令枪响，然后同时开跑。
     *      做法是初始化一个共享的CountDownLatch(1)，将其计数器初始化为1，
     *      多个线程在开始执行任务前首先 coundownlatch.await()，
     *      当主线程调用 countDown() 时，计数器变为0，多个线程同时被唤醒。
     */
    private static CountDownLatch count = null;

    private static AtomicInteger num = new AtomicInteger(0);

    synchronized void m1(){
        for (int i = 0; i < 5; i++) {
            num.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        TestCountDownLatch tl = new TestCountDownLatch();
        for (int i = 0; i < 100; i++) {
            listThread.add(new Thread(() -> {
                tl.m1();
                count.countDown();
            }, "thread " + i));
        }
        count = new CountDownLatch(listThread.size());
        listThread.stream().forEach(t -> {
            t.start();
        // TODO count在这里执行countDown,有线程安全问题，可能线程还么执行完已经countDown了
        // count.countDown();
        });


        // 等待线程执行完在往下进行
//        listThread.stream().forEach(t -> {
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });

        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thraed end, 最总结果num:" + num.get());
    }

}
