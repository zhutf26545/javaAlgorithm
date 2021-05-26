package com.zxx.multithreading;

/**
 * @Package: com.zxx.multithreading
 * @FileName: SyncThread02
 * @date 2021/5/26 10:28
 */
public class SyncThread02 {
    /**
     * 业务 两个线程，一个输出a-z,一个输出1-26 最终输出：a1b2c3...z26
     */
    public static void main(String[] args) {
        String c = "abcdefghijklmnopqrstuvwxyz";
        char[] chars = c.toCharArray();

        new Thread(() -> {
            synchronized (SyncThread02.class) {
                for (char value : chars) {
                    System.out.print(value);
                    SyncThread02.class.notify();
                    try {
                        SyncThread02.class.wait();
                        Thread.sleep(100);// 防止打印速度过快导致混乱
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }).start();

        new Thread(() -> {
            synchronized (SyncThread02.class) {
                for (int i = 1; i < 27; i++) {
                    System.out.print(i);
                    SyncThread02.class.notify();
                    try {
                        SyncThread02.class.wait();
                        Thread.sleep(100);// 防止打印速度过快导致混乱
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }).start();

    }

}
