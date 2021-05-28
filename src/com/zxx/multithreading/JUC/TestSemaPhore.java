package com.zxx.multithreading.JUC;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Package: com.zxx.multithreading
 * @FileName: TestSamePhore
 * @date 2021/5/23 15:52
 */
public class TestSemaPhore {
    /**
     * SemaPhore  限流  收费站，售票
     */
    static Semaphore semaphore = new Semaphore(2);

    public static void main(String[] args) {

        new Thread(() -> {
            try {
                semaphore.acquire();
                for (int i = 0; i < 3; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("one ~");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }).start();


        new Thread(() -> {
            try {
                semaphore.acquire();
                for (int j = 0; j < 3; j++) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("two ~");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }).start();


    }
}
