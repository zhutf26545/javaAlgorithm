package com.zxx.algorithmone.test;

/**
 * @author zxx
 * @version x.0
 * @Package: com.zxx.algorithmone.test
 * @FileName: AndOrTest
 * @date 2021/6/22 20:35
 */
public class AndOrTest {

    public static void main(String[] args) {
        // num >> 1  -> num / 2   (num >> n -> num / 2^n)
        /*int a = 2141234123;
        System.out.println(a >> 5);
        System.out.println(a / 32);*/

        // num << 1  -> num * 2   (num << n -> num * 2^n)
        /*int b = 32424;
        System.out.println(b << 3);
        System.out.println(b * 8);*/

        // num % 64 -> num & 63 (num % 32 -> num & 31)
        /*int c = 3225234;
        System.out.println(c % 64);
        System.out.println(c & 63);*/

        boolean d = true;
        boolean e = true;
        System.out.println(d != e);
        System.out.println(d ^ e);

    }
}
