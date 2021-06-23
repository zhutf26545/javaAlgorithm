package com.zxx.algorithmone.link;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.zxx.algorithmone.link
 * @FileName: OppositeLinkAndDoubleLink
 * @date 2021/6/3 20:26
 * <p>
 *     单链表，双链表逆序
 * </p>
 */
public class OppositeLinkAndDoubleLink {
    /**
     * 单链表
     */
    private static class Node {
        private Integer value = 0;
        private Node next = null;

        public Node(Integer v) {
            this.value = v;
        }
    }

    /**
     * 单个链表去反
     * @param n
     * @return
     */
    public static Node oppositeLink(Node n) {
        Node next = null;
        Node pro = null;
        while (n != null) {
            next = n.next;
            n.next = pro;
            pro = n;
            n = next;
        }
        return pro;
    }


    /**
     * 单个链表去反
     * @param n
     * @return
     */
    public static DoubleNode oppositeLink(DoubleNode n) {
        DoubleNode next = null;
        DoubleNode pro = null;
        while (n != null) {
            next = n.next;
            n.next = pro;
            pro = n;
            n = next;
        }
        return pro;
    }


    public static void main(String[] args) {
        int length = 10;
        int value = 1000;
        int forNum = 10000;
        for (int i = 0; i < forNum; i++) {
            Node link = getLink(length, value);
            List<Integer> list = getLinkedListOriginOrder(link);
            link = oppositeLink(link);
            if (!checkLinkedListReverse(list, link)){
                System.out.println("高咋啦1~");
            }
        }

        for (int i = 0; i < forNum; i++) {
            DoubleNode link = getDoubleLink(length, value);
            List<Integer> list = getLinkedListOriginOrder(link);
            link = oppositeDoubleLink(link);
            if (!checkLinkedListReverse(list, link)){
                System.out.println("高咋啦2~");
            }
        }

    }



    // for test
    public static List<Integer> getLinkedListOriginOrder(Node head) {
        List<Integer> ans = new ArrayList<>();
        while (head != null) {
            ans.add(head.value);
            head = head.next;
        }
        return ans;
    }

    // for test
    private static Node getLink(int length, int value) {
        int size = (int) (Math.random() * (length + 1));
        if (size == 0) {
            return null;
        }
        size--;
        Node head = new Node((int) (Math.random() * (value + 1)));
        Node pre = head;
        while (size != 0) {
            Node cur = new Node((int) (Math.random() * (value + 1)));
            pre.next = cur;
            pre = cur;
            size--;
        }
        return head;
    }

    // for test
    public static boolean checkLinkedListReverse(List<Integer> origin, DoubleNode head) {
        for (int i = origin.size() - 1; i >= 0; i--) {
            if (!origin.get(i).equals(head.value)) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    private static class DoubleNode {
        private Integer value;
        private DoubleNode prev;
        private DoubleNode next;

        public DoubleNode(Integer value) {
            this.value = value;
        }
    }


    /**
     * 双链表取反
     * @param link
     * @return
     */
    private static DoubleNode oppositeDoubleLink(DoubleNode link) {
        DoubleNode next = null;
        DoubleNode prev = null;
        while(link != null){
            next = link.next;
            link.next = prev;
            link.prev = next;
            prev = link;
            link = next;
        }
        return prev;
    }


    private static DoubleNode getDoubleLink(int length, int value) {
        int size = (int) (Math.random() * (length + 1));
        if (size == 0) {
            return null;
        }
        size--;
        DoubleNode head = new DoubleNode((int) (Math.random() * (value + 1)));
        DoubleNode pre = null;
        DoubleNode next = head;
        while (size != 0) {
            DoubleNode cur = new DoubleNode((int) (Math.random() * (value + 1)));
            next.next = cur;
            cur.prev = next;
            pre = next;
            next = cur;
            size--;
        }
        return head;
    }

    // for test
    public static List<Integer> getLinkedListOriginOrder(DoubleNode head) {
        List<Integer> ans = new ArrayList<>();
        while (head != null) {
            ans.add(head.value);
            head = head.next;
        }
        return ans;
    }

    // for test
    public static boolean checkLinkedListReverse(List<Integer> origin, Node head) {
        for (int i = origin.size() - 1; i >= 0; i--) {
            if (!origin.get(i).equals(head.value)) {
                return false;
            }
            head = head.next;
        }
        return true;
    }
}
