package com.zxx.algorithmone;

import java.util.Arrays;

/**
 * @Package: com.zxx.algorithmone
 * @FileName: testSort
 * @date 2021/5/19 19:46
 */
public class demoSort {
    static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 1; i < arr.length; i++) {
//            int newIndex = i;
//            while (newIndex > 0 && arr[newIndex - 1] > arr[newIndex]) {
//                swqp(arr, newIndex - 1, newIndex);
//                newIndex--;
//            }

            for (int j = i; j > 0 && arr[j - 1] > arr[j]; j--) {
                swqp(arr, j - 1, j);
            }

        }

    }

    private static void swqp(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }


    static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 1; j <= i; j++) {
                if (arr[j - 1] > arr[j]) {
                    swqp(arr, j - 1, j);
                }
            }
        }
    }

    static void selectSprt(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            int maxValueIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                maxValueIndex = arr[maxValueIndex] > arr[j] ? j : maxValueIndex;

            }
            swqp(arr, i, maxValueIndex);
        }

    }


    public static void bitInt(int i) {
        for (int j = 31; j >= 0 ; j--) {
            System.out.print( (i & 1<<j) == 0 ? "0": '1');
        }
    }

    public static void main(String[] args) {
//        int[] a = {1, 5, 2, 6, 3, -2, 6, 4, -8};
//
//        System.out.println(Arrays.toString(a));
//        selectSprt(a);
//        System.out.println(Arrays.toString(a));

        bitInt(15);
    }



}

