package com.zxx.algorithmone;

import java.util.Arrays;

/**
 * @Package: com.zxx.algorithmone
 * @FileName: algorithmone02
 * @date 2021/5/19 21:54
 */
public class algorithmone02 {

    /**
     * 一个数组中，随机某个范围求和，频繁请求
     * 1、得到一个数据，数据长度和入参数组一直，数据每个索引值，是传参数据索引前面总和
     * 请求某个随机和时，{3,7} =  0-7的值 - 0-2的值
     * 2、列一个矩阵，把所以的范围求和都算出来，直接取值
     */
    static int[] randmonRun(int[] arr) {
        int[] newarr = new int[arr.length];
        int num = 0;
        for (int i = 0; i < arr.length; i++) {
            num += arr[i];
            newarr[i] = num;
        }
        return newarr;
    }

    static int getrandmonRun(int[] arr, int start, int end) {
        return (end > arr.length || end == 0 || start > end) ? 0 : arr[end] - arr[start - 1];
    }


    public static void main(String[] args) {
//        int[] aaaa = {1, 4, 6, 2, 6, 7, 9, 5};
//        int[] ints = randmonRun(aaaa);
//        System.out.println(Arrays.toString(ints));
//
//        System.out.println("3-7: " + getrandmonRun(ints, 3, 7));


        int forNum = 10000000;
        int count = 0;
        for (int i = 0; i < forNum; i++) {
            // Math.random() -> [0,1)
            if (Math.random() < 0.4) {
                count++;
            }
        }
        System.out.println("Math.random(): " + (double) count / (double) forNum);

        System.out.println("-------------------------------");
        count = 0;
        for (int i = 0; i < forNum; i++) {
            // Math.random() * 4 -> [0,4)
            if (Math.random() * 4 < 1) {
                count++;
            }
        }
        System.out.println("Math.random() * 4: " + (double) count / (double) forNum);

        System.out.println("-------------------------------");
        count = 0;
        for (int i = 0; i < forNum; i++) {
            // (int)(Math.random() * 4) -> [0,3]
            if ((int) (Math.random() * 4) > 2) {
                count++;
            }
        }
        System.out.println("(int)(Math.random() * 4) : " + (double) count / (double) forNum);

        System.out.println("-------------------------------");
        count = 0;
        for (int i = 0; i < forNum; i++) {
            // (int)(Math.random() * 4) + 1 -> [1,4]
            if ((int) (Math.random() * 4) + 1 > 1) {
                count++;
            }
        }

        System.out.println("(int)(Math.random() * 4) + 1: " + (double) count / (double) forNum);

        System.out.println("-------------------------------");
        count = 0;
        for (int i = 0; i < forNum; i++) {
            if (xToXPow2() < 0.5) {
                count++;
            }
        }

        System.out.println("xToXPow2: " + (double) count / (double) forNum);
        System.out.println("Math.pow: " + Math.pow(0.5, 2));

        System.out.println("----------(1-5 -> 0-1)---------------------");
        count = 0;
        for (int i = 0; i < forNum; i++) {
            if (zeroAndOne() == 1) {
                count++;
            }
        }

        System.out.println("zeroAndOne: " + (double) count / (double) forNum);

        System.out.println("--------------(0-1 -> 0-7 概率)-----------------");
        int[] arr = new int[8];
        for (int i = 0; i < forNum; i++) {
            arr[F7()] = arr[F7()]++;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i + "(0-1 -> 0-7 概率)出现次数：" + arr[i]);
        }

        System.out.println("--------------(0-7 -> 1-7 概率)-----------------");
        int[] arr1 = new int[8];
        for (int i = 0; i < forNum; i++) {
            arr1[F1_7()] = arr1[F1_7()]++;
        }
        for (int i = 0; i < arr1.length; i++) {
            System.out.println(i + "(0-7 -> 1-7 概率)出现次数：" + arr1[i]);
        }

    }


    /**
     * 对数器
     */
    public static double xToXPow2() {
        return Math.max(Math.random(), Math.random());
    }

    /**
     * 给定一个1-5等概率的函数，用这个函数表示出支持1-7的等概率函数
     */
    public static int F5() {
        return (int) (Math.random() * 5 + 1);
    }

    public static int zeroAndOne() {
        int num = 0;
        do {
            num = F5();
        } while (num == 3);
        return num < 3 ? 0 : 1;
    }

    public static int F7() {
        return (zeroAndOne() << 2) + (zeroAndOne() << 1) + zeroAndOne();
    }

    public static int F1_7() {
        int num = 0;
        do {
            num = F7();
        } while (num == 0);
        return num;
    }
}
