package com.zxx.multithreading.JUC;

/**
 * @Package: com.zxx.multithreading
 * @FileName: Thread_sleep_yield_join
 * @date 2021/5/20 15:28
 */
public class Thread_sleep_yield_join {

    static void tastSleep() {
        new Thread(() -> {
            try {
                Thread.sleep(1500);
                System.out.println("I'm Sleep Thread!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

        new Thread(() -> {
                System.out.println("I'm run Thread!");
        }).start();
    }


    static void testYield(){
        new Thread(() -> {
            for (int i = 0; i < 10 ; i++) {
                if (Math.random() < 0.3) {
                    Thread.yield();
                    System.out.println("I'm ready run!");
                }
                System.out.println("I'm normal run!");
            }

        }).start();
    }

   static void tastJoin() {
       Thread t1 = new Thread(() -> {
           try {
               Thread.sleep(1500);
               System.out.println("I'm Sleep Thread!");
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       });

       Thread t2 = new Thread(() -> {
           try {
               t1.join();

           } catch (InterruptedException e) {
               t1.interrupt();
               e.printStackTrace();
           }
           System.out.println("I'm t2 Thread!");
       });

       t1.start();
       t2.start();
   }

    public static void main(String[] args) {
        tastJoin();
    }
}
