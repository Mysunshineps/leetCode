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

    /**
     *  用两个栈实现队列
     *  题目：用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
     */
    class day9{
        /**
         * 方法：双栈
         * 解题思路：
         * 维护两个栈，第一个栈支持插入操作，第二个栈支持删除操作。
         * 根据栈先进后出的特性，我们每次往第一个栈里插入元素后，第一个栈的底部元素是最后插入的元素，第一个栈的顶部元素是下一个待删除的元素。为了维护队列先进先出的特性，
         * 我们引入第二个栈，用第二个栈维护待删除的元素，在执行删除操作的时候我们首先看下第二个栈是否为空。如果为空，我们将第一个栈里的元素一个个弹出插入到第二个栈里，这样第二个栈里元素的顺序就是待删除的元素的顺序，要执行删除操作的时候我们直接弹出第二个栈的元素返回即可。
         *
         * 复杂度分析：
         * 时间复杂度：对于插入和删除操作，时间复杂度均为 O(1)。插入不多说，对于删除操作，虽然看起来是 O(n) 的时间复杂度，但是仔细考虑下每个元素只会「至多被插入和弹出 stack2 一次」，因此均摊下来每个元素被删除的时间复杂度仍为 O(1)。
         * 空间复杂度：O(n)。需要使用两个栈存储已有的元素。
         *
         */
        class CQueue {
            /**
             * todo：补充栈的一些方法
             * boolean empty( )-——-如果堆栈是空的，则返回true，当堆栈包含元素时，返回false。
             * Object peek( )———–返回位于栈顶的元素，但是并不在堆栈中删除它。
             * Object pop( )————返回位于栈顶的元素，并在进程中删除它。
             * Object push (Object element )———将element压入堆栈，同时也返回element。
             * int search(Object element)———在堆栈中搜索element，如果发现了，则返回它相对于栈顶
             */
            Deque<Integer> in = null;
            Deque<Integer> delete = null;
            public CQueue() {
                in =  new LinkedList<>();
                delete =  new LinkedList<>();
            }

            public void appendTail(int value) {
                in.push(value);
            }

            public int deleteHead() {
                if(null != delete.peek()){
                    return delete.pop();
                }
                while(null != in.peek()){
                    delete.push(in.pop());
                }

                if(null != delete.peek()){
                    return delete.pop();
                }else{
                    return -1;
                }
            }
        }
    }

}
