package com.zxx.algorithmone.bitmap;

import java.util.BitSet;
import java.util.HashSet;

/**
 * @author zxx
 * @version x.0
 * @Package: com.zxx.algorithmone.bitmap
 * @FileName: BitMapcollection1
 * @date 2021/6/23 9:07
 */
public class BitMapcollection1 {
    /**
     * int 4 字节。可以表示数字 0-31
     * long 8 字节。 可以表示数字 0-63
     */

    int[] bitMap;

    public BitMapcollection1(int num) {
        // n / 32 -> n >> 5
        bitMap = new int[(num + 32) >> 5];
    }

    public void add(int num) {
        // num % 64 -> num & 63 (num % 32 -> num & 31)
        // 63  32+16+8+4+2+1
        // 00111111  -> 63
        // 00011111  -> 31
        // 1 << (num & 5)
        bitMap[num >> 5] |= (1 << (num & 31));
    }

    public void delete(int num) {
        bitMap[num >> 5] &= ~(1 << (num & 31));
    }

    public boolean contains(int num) {
        // (1 << (num & 31))   00010000
        // bitMap[num >> 5]    10010010
        return (bitMap[num >> 5] & (1 << (num & 31))) != 0;
    }


    public static void main(String[] args) {
        System.out.println("测试开始！");
        int max = 10000;
        BitMapcollection1 bitMap = new BitMapcollection1(max);
        HashSet<Integer> set = new HashSet<>();
        int testTime = 10000000;
        for (int i = 0; i < testTime; i++) {
            int num = (int) (Math.random() * (max + 1));
            double decide = Math.random();
            if (decide < 0.333) {
                bitMap.add(num);
                set.add(num);
            } else if (decide < 0.666) {
                bitMap.delete(num);
                set.remove(num);
            } else {
                if (bitMap.contains(num) != set.contains(num)) {
                    System.out.println("Oops!");
                    break;
                }
            }
        }
        for (int num = 0; num <= max; num++) {
            if (bitMap.contains(num) != set.contains(num)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束！");
    }

}
