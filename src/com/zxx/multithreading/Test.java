package com.zxx.multithreading;

import sun.misc.Unsafe;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @Package: com.zxx.multithreading
 * @FileName: Test
 * @date 2021/5/22 17:42
 */
public class Test {


    static Thread t1 = null;
    static Thread t2 = null;
    public static void main(String[] args) {
        t1 = new Thread(() -> {
            System.out.println("t1  start ~");
            try {
                t2.join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1  end !");
        },"T1");
        t2 = new Thread(() -> {
            System.out.println("t2  start ~");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2  end !");
        },"T2");
        getThreadStatus(t1);
        getThreadStatus(t2);
        t1.start();
        t2.start();
        getThreadStatus(t1);
        getThreadStatus(t2);

    }

    static void getThreadStatus(Thread t) {
        System.out.println(t.getName() + "  status: " + t.getState().toString());
    }
}
