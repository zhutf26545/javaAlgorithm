package com.zxx.multithreading.JUC;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Package: com.zxx.multithreading
 * @FileName: TestExchenger
 * @date 2021/5/23 16:01
 */
public class TestExchenger {
    static Exchanger<Map> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(() -> {
            Map<String, Object> map1 = new HashMap<>();
            map1.put("name", "lulala");
            map1.put("age", 18);
            try {
                map1 = exchanger.exchange(map1);
                System.out.println(Thread.currentThread().getName() + " -- " + map1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "T1").start();
        new Thread(() -> {
            Map<String, Object> map2 = new HashMap<>();
            map2.put("name", "hahaha");
            map2.put("age", 20);
            try {
                map2 = exchanger.exchange(map2);
                System.out.println(Thread.currentThread().getName() + " -- " + map2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "T2").start();

    }
}
