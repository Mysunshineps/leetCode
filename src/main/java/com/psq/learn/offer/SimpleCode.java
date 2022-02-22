package com.psq.learn.offer;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Description 简单程度
 * @Author psq
 * @Date 2022/2/15 16:59
 */
public class SimpleCode {

    public static void main(String[] args) {
        System.out.println("Hello offer-SimpleCode!");
    }

    /**
     * 从尾到头打印链表
     * 题目：输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
     */
    class day7{
        public class ListNode {
            int val;
            ListNode next;
            ListNode(int x) { val = x; }
        }

        /**
         * 方法一：栈
         * 解题思路：
         * 栈的特点是后进先出，即最后压入栈的元素最先弹出。考虑到栈的这一特点，使用栈将链表元素顺序倒置。从链表的头节点开始，依次将每个节点压入栈内，然后依次弹出栈内的元素并存储到数组中。
         *
         * 复杂性分析：
         * 时间复杂度：O(n)。正向遍历一遍链表，然后从栈弹出全部节点，等于又反向遍历一遍链表。
         * 空间复杂度：O(n)。额外使用一个栈存储链表中的每个节点。
         */
        public int[] reversePrint(ListNode head) {
            if(null == head){
                return new int[]{};
            }
            Deque<Integer> resultList = new LinkedList<Integer>();
            while(null != head){
                resultList.push(head.val);
                head = head.next;
            }
            int[] result = new int[resultList.size()];
            int i=0;
            while(null != resultList.peek()){
                result[i] = resultList.pop();
                i++;
            }
            return result;
        }
    }
}
