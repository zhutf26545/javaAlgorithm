package com.zxx.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Package: com.zxx.multithreading
 * @FileName: TestCyclicBarrier
 * @date 2021/5/23 13:43
 */
public class TestCyclicBarrier {

    /**
     * CyclicBarrier 栅栏
     * 类似于公司团建，有X个人报名参加，到了团建出发那天，必须等人到齐了在进行活动
     */
    static int peopleNum = 5;
    private static CyclicBarrier cb = new CyclicBarrier(peopleNum, () ->
            System.out.println("人到齐了，出发吧！")
    );
    private static AtomicInteger num = new AtomicInteger(0);

    /**
     * CountDownLatch和CyclicBarrier都有让多个线程等待同步然后再开始下一步动作的意思，
     * 但是CountDownLatch的下一步的动作实施者是当前线程，具有不可重复性；
     * 而CyclicBarrier的下一步动作实施者还是“其他线程”本身，具有往复多次实施动作的特点。
     * @param args
     */
    public static void main(String[] args) {
        TestCyclicBarrier tcb = new TestCyclicBarrier();
        for (int i = 0; i < peopleNum; i++) {
           new Thread(() -> {
               try {
                   System.out.println("第" + num.incrementAndGet() + "人到了！");
                   cb.await();
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }).start();
        }

        // Cyclicbarrier不会阻塞主线程运行
        System.out.println("I'm main!");
    }
}
