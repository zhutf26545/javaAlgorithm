package com.zxx.algorithmone;

import java.util.Arrays;
import java.util.Random;

/**
 * @Package: com.zxx.algorithmone
 * @FileName: BinarySearch01
 * @date 2021/5/23 19:47
 * <p>
 * 1、有序数据中是否包含某个值
 * 2、有序数据中<=num 位置
 * 3、有序数据中>=num 位置  √
 * 4、局部最小 相邻不相等
 * </p>
 */
public class BinarySearch03 {

    static int findRightNum(int[] arr, int num) {
        if (arr == null || arr.length == 0 ||
                arr[0] > num || arr[arr.length - 1] < num) {
            return -1;
        }
        int l = 0;
        int r = arr.length - 1;
        int index = -1;
        while (l <= r) {
            int min = (l + r) / 2;
            if (arr[min] <= num) {
                l = min + 1;
                index = arr[min] == num ? min : index;
            } else if (arr[min] > num) {
                r = min - 1;
            }
        }
        return index;
    }

    static int existsByForeach(int[] arr, int num) {
        if (arr == null || arr.length == 0 ||
                arr[0] > num || arr[arr.length - 1] < num) {
            return -1;
        }
        int index = -1;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == num) {
                index = i;
                break;
            }
        }
        return index;
    }

    static int[] getInt(int length, int value) {
        Random r = new Random();
        int arrL = r.nextInt(length);
        if (arrL < 2) {
            return getInt(length, value);
        }
        int[] a = new int[arrL];
        for (int i = 0; i < arrL; i++) {
            a[i] = (int) (Math.random() * value);
        }
        return a;
    }

    static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j - 1] > arr[j]; j--) {
                swap(arr, j - 1, j);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static void main(String[] args) {
        int length = 20;
        int value = 100;
        Boolean ok = true;
        for (int i = 0; i < 1000; i++) {
            int[] arr = getInt(length, value);
            insertSort(arr);
            Random r = new Random();
            int num = r.nextInt(value);
            try {
                if (findRightNum(arr, num) != existsByForeach(arr, num)) {
                    System.out.println("不一致， findRightNum(arr, num): " + findRightNum(arr, num) + ", existsByForeach(arr, num) ： " + existsByForeach(arr, num));
                    System.out.println(Arrays.toString(arr));
                    System.out.println("num：" + num);
                    ok = false;
                    break;
                }
            } catch (Exception e) {
                System.out.println("aaa: " + Arrays.toString(arr));
                System.out.println("num: " + num);
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

        }
        System.out.println(ok ? "Nice" : "Facking");


//        int[] arr = new int[]{10, 23, 31, 37, 79, 80, 83, 83};
//        int rightNum = findRightNum(arr, 67);
//        int i = existsByForeach(arr, 50);
//        System.out.println("rightNum:" + rightNum + ", i:" + i);

    }
}
