package com.zxx.algorithmone;

import java.util.Arrays;

/**
 * @Package: com.zxx.algorithmone
 * @FileName: SelectionSort
 * @date 2021/5/19 9:14
 */
public class algorithmone01 {


    /**
     * 选择排序
     * [22,5,1,45,0,3,4]
     * 第一个和所有的比较，将最小的和第一个位置互换，第一个数就是最小。
     * 接下来那第二个数和所有数比较... 依次轮推。最后得到一个有序的数据
     *
     * @param arr
     */
    public static void selectSort(int[] arr) {
        // 先判断有界性
        if (arr == null || arr.length < 2) {
            return;
        }
        int minnum = 0;
        for (int i = 0; i < arr.length; i++) {
            minnum = i;
            for (int j = i + 1; j < arr.length; j++) {
                minnum = arr[minnum] > arr[j] ? j : minnum;
            }
            swap(arr, i, minnum);
        }

    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }


    /**
     * 冒泡排序
     * [22,5,1,45,0,3,4]
     * 0 1 2 3 4 5 6
     * 0 和 1进行比较，将大的放到后面；1 和 2进行比较，将大的放到后面； 依次轮推，最后一个就是最大值（n-1）;  0 - n-1
     * 0 和 1进行比较，将大的放到后面；1 和 2进行比较，将大的放到后面； 依次轮推，最后一个就是最大值（n-2）;  0 - n-2
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        // 先判断有界性
        if (arr == null || arr.length < 2) {
            return;
        }
        // 0 - n-1
        // 0 - n-2
        // 最大值确定了，下次范围就是 0 - （n - 1) - 1
        int arrLength = arr.length;
        for (int i = arrLength - 1; i >= 0; i--) {
            // 0 1 1 2 2 3 ... n-1 n
            for (int j = 1; j <= i; j++) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                }
            }
        }

    }


    /**
     * 获取一个int类型的二进位数
     * 第一位是符号符 0 非负 1 正
     * 0 后面31位进行相加， 1 （全部取反 + 1）、（第一个符号位去掉，后面31位取反 + 1）
     * int 范围 -2^31 - 2^31-1  0 归于非负，所以 正数绝对值比负数小
     * <p>
     * ??? 为什么是 2^31 - 1
     * 32位 第一个符号位不算，后面全1正好是2^31-1
     *
     * @param t
     */
    public static void getBitNum(int t) {
        System.out.println();
        for (int i = 31; i >= 0; i--) {
            System.out.print((t & (1 << i)) == 0 ? "0" : "1");
        }
    }

    /**
     * 插入排序
     * [22,5,1,45,0,3,4]
     * 0 1 2 3 4 5 6
     * 0 - 0 √
     * 0 - 1 √
     * 0 - 2 √
     * 0 - n-1 √
     *
     * @param arr
     */
    public static void insertSort(int[] arr) {
        // 先判断有界性
        if (arr == null || arr.length < 2) {
            return;
        }
        int arrLength = arr.length;
        for (int end = 1; end < arrLength; end++) {
//            int newArrIndex = end;
//            while (newArrIndex > 0 && arr[newArrIndex - 1] > arr[newArrIndex]) {
//                swap(arr, newArrIndex - 1, newArrIndex);
//                newArrIndex--;
//            }

            for (int pre = end; pre > 0 && arr[pre - 1] > arr[pre]; pre--) {
                swap(arr, pre - 1, pre);
            }
        }

    }

    public static void main(String[] args) {

        int[] a = {7, 2, 5, 1, 6, 8, 2, 5, 7, 8, -1};

        System.out.println(Arrays.toString(a));
//        selectSort(a);
//        bubbleSort(a);
        insertSort(a);
        System.out.println(Arrays.toString(a));

//        getBitNum(123);
//        getBitNum(456);
//        getBitNum(123 & 456); // 与 全1出1
//        getBitNum(123 | 456); // 或 有1出1
//        getBitNum(~123); // 非 有1出0, 有0出1
//        getBitNum(123 ^ 456); // 异或 相同则结果为0，不同则结果为1

//        getBitNum(Integer.MAX_VALUE);

    }
}
