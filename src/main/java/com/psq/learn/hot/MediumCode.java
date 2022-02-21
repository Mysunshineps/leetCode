package com.psq.learn.hot;

import java.util.HashMap;

/**
 * @Description 中等程度
 * @Author psq
 * @Date 2022/2/15 17:00
 */
public class MediumCode {
    public static void main(String[] args) {
        System.out.println("Hello hot-MediumCode!");
    }

    /**
     * 两数相加
     * 题目：
     * 给你两个非空的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * 你可以假设除了数字 0 之外，这两个数都不会以0开头
     */
    class day2 {
        public class ListNode {
            int val;
            ListNode next;

            ListNode() {
            }

            ListNode(int val) {
                this.val = val;
            }

            ListNode(int val, ListNode next) {
                this.val = val;
                this.next = next;
            }
        }

        /**
         * TODO：方法一题解：模拟
         * 解题思路：
         * 由于输入的两个链表都是逆序存储数字的位数的，因此两个链表中同一位置的数字可以直接相加。
         * 我们同时遍历两个链表，逐位计算它们的和，并与当前位置的进位值相加。具体而言，如果当前两个链表处相应位置的数字为 n1,n2，进位值为 carry，则它们的和为 sum = n1+n2+carry
         * 其中，答案链表处相应位置的数字为sum%10(取模),而新的进位值为sum/10(取整)
         * 如果两个链表的长度不同，则可以认为长度短的链表的后面有若干个0 。
         * 此外，如果链表遍历结束后，有进位carry>0，还需要在答案链表的后面附加一个节点，节点的值为 carry
         *
         * 复杂度分析：
         * 时间复杂度：(max(m,n))，其中 mm 和 nn 分别为两个链表的长度。我们要遍历两个链表的全部位置，而处理每个位置只需要 O(1) 的时间。
         * 空间复杂度：O(1)。注意返回值不计入空间复杂度。
         */
        public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
            ListNode head = null, tail = null;
            //进位
            int carry = 0;
            while (l1 != null || l2 != null){
                int a = l1 != null ? l1.val : 0;
                int b = l2 != null ? l2.val : 0;
                int sum = a + b + carry;
                if (head == null){
                    head = tail =  new ListNode(sum%10);
                }else {
                    tail.next =  new ListNode(sum%10);
                    tail = tail.next;
                }
                carry = sum/10;
                if (l1 != null){
                    l1 = l1.next;
                }
                if (l2 != null){
                    l2 = l2.next;
                }
            }
            if (carry > 0){
                tail.next = new ListNode(carry);
            }
            return head;
        }

        /**
         * TODO：方法二题解：链表
         * 解题思路：
         * 将两个链表看成是相同长度的进行遍历，如果一个链表较短则在前面补0，比如 987 + 023 = 1010
         * 每一位计算的同时需要考虑上一位的进位问题，而当前位计算结束后同样需要更新进位值
         * 如果两个链表全部遍历完毕后，进位值为 1，则在新链表最前方添加节点 1
         * 小技巧：对于链表问题，返回结果为头结点时，通常需要先初始化一个预先指针 pre，该指针的下一个节点指向真正的头结点head。使用预先指针的目的在于链表初始化时无可用节点值，而且链表构造过程需要指针移动，进而会导致头指针丢失，无法返回结果。
         */
        public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
            ListNode pre = new ListNode(0);
            ListNode cur = pre;
            int carry = 0;
            while(l1 != null || l2 != null) {
                int x = l1 == null ? 0 : l1.val;
                int y = l2 == null ? 0 : l2.val;
                int sum = x + y + carry;

                carry = sum / 10;
                sum = sum % 10;
                cur.next = new ListNode(sum);

                cur = cur.next;
                if(l1 != null)
                    l1 = l1.next;
                if(l2 != null)
                    l2 = l2.next;
            }
            if(carry == 1) {
                cur.next = new ListNode(carry);
            }
            return pre.next;
        }

    }

    /**
     * 无重复字符的最长子串长度
     * 题目：
     * 给定一个字符串s，请你找出其中不含有重复字符的 最长子串 的长度
     */
    class day3{
        /**
         * 方法一：滑动窗口
         * 其实就是一个队列,比如例题中的 abcabcbb，进入这个队列（窗口）为 abc 满足题目要求，当再进入 a，队列变成了 abca，这时候不满足要求。所以，我们要移动这个队列！
         * 如何移动？
         * 我们只要把队列的左边的元素移出就行了，直到满足题目要求！
         * 一直维持这样的队列，找出队列出现最长的长度时候
         *
         * 复杂度分析：
         * 时间复杂度：O(n)
         */
        public int lengthOfLongestSubstring(String s) {
            if (s.length()==0) {
                return 0;
            }
            HashMap<Character, Integer> map = new HashMap<>();
            int max = 0;
            int left = 0;
            for(int i = 0; i < s.length(); i ++){
                if(map.containsKey(s.charAt(i))){
                    left = Math.max(left,map.get(s.charAt(i)) + 1);
                }
                map.put(s.charAt(i),i);
                max = Math.max(max,i-left+1);
            }
            return max;
        }
    }
}
