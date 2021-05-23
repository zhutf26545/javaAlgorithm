package com.zxx.multithreading;

import java.util.concurrent.Phaser;

/**
 * @Package: com.zxx.multithreading
 * @FileName: testPhaser
 * @date 2021/5/23 15:41
 */
public class testPhaser {
    class MyPhaser extends Phaser {
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {

            switch (phase) {
                case 0:
                    System.out.println("0- 结束");
                    return false;

                case 1:
                    System.out.println("1- 结束");

                    return false;
                case 2:
                    System.out.println("2- 结束");

                    return false;
                case 3:
                    System.out.println("3- 结束");
                    return true;
                default:
                    return true;
            }
        }
    }
}
