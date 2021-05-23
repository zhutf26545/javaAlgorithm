package com.zxx.multithreading;

import sun.misc.Unsafe;

/**
 * @Package: com.zxx.multithreading
 * @FileName: Test
 * @date 2021/5/22 17:42
 */
public class Test {
    private class T1{
        private Integer age = 19;
    }

    public static void main(String[] args) {
        Unsafe unsafe = Unsafe.getUnsafe();
        T1 t = null;
        try {
            t = (T1) unsafe.allocateInstance(T1.class);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        t.age = 20;
        System.out.println("age" + t.age);
    }
}
