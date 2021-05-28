package com.zxx.multithreading.JUC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Package: com.zxx.multithreading
 * @FileName: ReentrantLock03
 * @date 2021/5/26 9:32
 */
public class ReentrantLock03 {
    /**
     * 业务： 模拟两个线程，一个线程输出A-Z,一个线程输出1-26，一个线程输出小写字母a-z 最终输出 A1aB2b...Z26z
     */
    private static Integer num = 1; // 1 输出大写字母 2输出数字 3 小写字母
    private static Lock lock = new ReentrantLock();
    private static Condition c1 = lock.newCondition();
    private static Condition c2 = lock.newCondition();
    private static Condition c3 = lock.newCondition();
    String c = "ABCDEFGHIJKLMNOPQRSEUVWXYZ";

    public void printUpperChar() {
        char[] chars = c.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            lock.lock();
            try {
                while (num != 1) {
                    c1.await();
                }
                System.out.print(chars[i]);
                num = 2;
                c2.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public void printInt() {
        for (int i = 1; i < 27; i++) {
            lock.lock();
            try {
                while (num != 2) {
                    c2.await();
                }
                System.out.print(i);
                num = 3;
                c3.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public void printLowerChar() {
        String lowerC= c;
        char[] chars = lowerC.toLowerCase().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            lock.lock();
            try {
                while (num != 3) {
                    c3.await();
                }
                System.out.print(chars[i]);
                num = 1;
                c1.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLock03 lock03 = new ReentrantLock03();
        new Thread(() -> lock03.printUpperChar()).start();
        new Thread(() -> lock03.printInt()).start();
        new Thread(() -> lock03.printLowerChar()).start();
    }
}
