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
 * 3、有序数据中>=num 位置
 * 4、局部最小 相邻不相等 √
 * </p>
 */
public class BinarySearch04 {
    /**
     * 冒泡排序
     * 按值传递，传递的是对象的引用，实际修改的是对象值
     *
     * @param arr
     * @return
     */
    public static void bubbleSort(int[] arr) {
        if (arr == null && arr.length < 2) {
            return;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 1; j < i; j++) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static int[] getInt(int length, int value) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    private static boolean checkInt(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取相邻位置，不相等的数组
     * @param length
     * @param value
     * @return
     */
    public static int[] getInt1(int length, int value) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * value);
            while (i > 0 && arr[i - 1] == arr[i]) {
                arr[i] = (int) (Math.random() * value);
            }
        }
        return arr;
    }

    /**
     * 校验相邻位置，不相等的数组
     * @param arr
     * @return
     */
    private static boolean checkInt1(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == arr[i + 1]) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int length = 10;
        int value = 100;
//        for (int i = 0; i < 1000; i++) {
//            int[] arr = getInt(length, value);
//            bubbleSort(arr);
//            if (!checkInt(arr)) {
//                System.out.println(Arrays.toString(arr));
//                break;
//            }
//        }


//        for (int i = 0; i < 10000; i++) {
//            int[] arr1 = getInt(length, value);
//            bubbleSort(arr1);
//            int randomNUM = new Random().nextInt(value);
//            int index = findLeftNum(arr1, randomNUM);
//            if (index > 0) {
//                System.out.println(Arrays.toString(arr1));
//                System.out.println("randomNUM: " + randomNUM + ", index:" + index);
//            }
//        }


        for (int i = 0; i < 10; i++) {
            int[] arr1 = getInt1(length, value);
            System.out.println(Arrays.toString(arr1));
            int randomNum = findRandomNum(arr1);
            System.out.println("randomNum:" + randomNum);

        }
    }



    // 有序数据中是否包含某个值
    private static boolean findIntNum(int[] arr, int value) {
        if (arr == null || (arr.length == 1 && arr[0] != value)) {
            return false;
        }
        /**
         * 二分查找
         * 获取中心位置索引，如果中心位置值正好等于value直接返回
         * 如果中心位置值小于value, l = min + 1
         */
        int l = 0;
        int r = arr.length - 1;
        int min = 0;
        while (l <= r) {
            min = (l + r) / 2;
            if (arr[min] < value) {
                l = min + 1;
            } else if (arr[min] > value) {
                r = min - 1;
            } else if (arr[min] == value) {
                return true;
            }
        }
        return false;
    }


    // 有序数据中<=num 位置, 寻找最左边的数位置
    private static int findLeftNum(int[] arr, int value) {
        if (arr == null || (arr.length == 1 && arr[0] != value)) {
            return -1;
        }
        int l = 0;
        int r = arr.length - 1;
        int min = 0;
        int index = -1;
        while (l <= r) {
            min = (l + r) / 2;
            if (arr[min] >= value) {
                r = min - 1;
                index = arr[min] == value ? min : index;
            } else if (arr[min] < value) {
                l = min + 1;
            }
        }
        return index;
    }

    private static int findRandomNum(int[] arr) {
        if (arr == null || arr.length == 1) {
            return -1;
        }
        int length = arr.length;
        if (arr[0] <= arr[1] || arr[length - 2] >= arr[length - 1]) {
            return -1;
        }
        int l = 0;
        int r = length - 1;
        int min = (l + r) / 2;
        while (l <= r) {
            min = (l + r) / 2;
            if (arr[min - 1] < arr[min] && arr[min] < arr[min + 1]) {
                return min;
            } else if (arr[min - 1] < arr[min]) {
                r = min - 1;
            } else if (arr[min] > arr[min + 1]) {
                l = min + 1;
            }
        }
        return -1;
    }
}
