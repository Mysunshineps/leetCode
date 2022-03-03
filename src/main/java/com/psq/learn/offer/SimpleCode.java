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
//        System.out.println(result);
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

    /**
     * 青蛙跳台阶问题
     * 题目：一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     */
    class day10{
        /**
         * 解题思路：
         * 此类求 多少种可能性 的题目一般都有 递推性质 ，即 f(n)f(n) 和 f(n-1)f(n−1)…f(1)f(1) 之间是有联系的。
         * 设跳上 n 级台阶有 f(n) 种跳法。在所有跳法中，青蛙的最后一步只有两种情况： 跳上 1 级或 2 级台阶。
         * 当为 1 级台阶： 剩 n−1 个台阶，此情况共有 f(n−1) 种跳法；
         * 当为 2 级台阶： 剩 n−2 个台阶，此情况共有 f(n−2) 种跳法。
         * f(n)f 为以上两种情况之和，即 f(n)=f(n-1)+f(n-2) ，以上递推性质为斐波那契数列。本题可转化为 求斐波那契数列第 n 项的值 ，与 斐波那契数列 等价，唯一的不同在于起始数字不同。
         * 青蛙跳台阶问题： f(0)=1 , f(1)=1 , f(2)=2 ；
         * 斐波那契数列问题： f(0)=0 , f(1)=1, f(2)=1。
         *
         * 一、递归法：
         * 原理： 把 f(n) 问题的计算拆分成 f(n-1)和 f(n-2) 两个子问题的计算，并递归，以 f(0) 和 f(1) 为终止条件。
         * 缺点： 大量重复的递归计算，例如 f(n)f 和 f(n - 1) 两者向下递归都需要计算 f(n - 2)f 的值。
         * 二、记忆化递归法：
         * 原理： 在递归法的基础上，新建一个长度为 n 的数组，用于在递归时存储 f(0)至 f(n) 的数字值，重复遇到某数字时则直接从数组取用，避免了重复的递归计算。
         * 缺点： 记忆化存储的数组需要使用 O(N)O 的额外空间。
         * 三、动态规划：
         * 原理： 以斐波那契数列性质 f(n + 1) = f(n) + f(n - 1) 为转移方程。
         * 从计算效率、空间复杂度上看，动态规划是本题的最佳解法。
         */
        public int numWays(int n) {
            int a = 1000000007;
            int sum = 1, p=1, q=1;
            for(int i=0; i<n; i++){
                sum = (q+p)%a;
                p=q;
                q=sum;
            }
            return p;
        }
    }

    /**
     * 旋转数组的最小数字
     * 题目：把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 给你一个可能存在 重复 元素值的数组 numbers ，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。请返回旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一次旋转，该数组的最小值为1
     */
    class day11{
        /**
         * todo：个人答题的方法
         * 方法一：动态规划，依次递减
         * 解题思路：
         * 依次取第一个pre和最后一个下标last的值，依次对比，若pre下标的值大于last下标的值，将last赋给pre,last减1继续对比，直到pre下标的值小于last下标的值,则pre下标的值为最小值
         * 注意数组越界的问题，当last小于0时，则跳出
         *
         * 复杂度分析：
         * 时间复杂度：平均时间复杂度为 O(n)，其中 n 是数组 numbers 的长度。如果数组是随机生成的。而最坏情况下，那么while 循环就需要执行 n 次，时间复杂度为 O(n)。
         * 空间复杂度：O(1)。
         */
        public int minArray(int[] numbers) {
            if(numbers.length == 1){
                return numbers[0];
            }
            int length = numbers.length-1;
            int pre = 0;
            while(numbers[pre] >= numbers[length]){
                pre = length;
                length--;
                if(length < 0){
                    break;
                }
            }
            return numbers[pre];
        }

        /**
         * todo:官方解答
         * 方法二：二分查找
         *  解题思路：
         *  最小值左边的值大于等于最后位置的数；最小值右边的值小于等于最后位置的数
         *  因此：每次取最高下标high的值与数组中间下标pivot的值作对比，若下标pivot的值大于下标high的值，最小值在下标pivot的右方；反之小于则在左方；等于时将下标high左移一位再比较；直到找到最小值
         *
         *  复杂度分析：
         * 时间复杂度：平均时间复杂度为O(logn)，其中 n 是数组numbers 的长度。如果数组是随机生成的，那么数组中包含相同元素的概率很低，在二分查找的过程中，大部分情况都会忽略一半的区间。而在最坏情况下，如果数组中的元素完全相同，那么while 循环就需要执行 n 次，每次忽略区间的右端点，时间复杂度为 O(n)。
         * 空间复杂度：O(1)
         */
        public int minArray2(int[] numbers) {
            int low = 0;
            int high = numbers.length - 1;
            while (low < high) {
                //获取中间下标
                int pivot = low + (high - low) / 2;
                if (numbers[pivot] < numbers[high]) {
                    high = pivot;
                } else if (numbers[pivot] > numbers[high]) {
                    low = pivot + 1;
                } else {
                    high --;
                }
            }
            return numbers[low];
        }

    }

    /**
     *  二进制中1的个数
     *  题目：编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为 汉明重量).
     */
    class day16{
        /**
         * 方法：位运算优化[与（&）、非（~）、或（|）、异或（^）]
         * 解题思路：观察这个运算：n&(n - 1)，其预算结果恰为把 n 的二进制位中的最低位的 1 变为 0 之后的结果。
         * 如：6 = 二进制(110), 4 = 二进制(100)；6&(6-1) = 4,
         * 这样我们可以利用这个位运算的性质加速我们的检查过程，在实际代码中，我们不断让当前的 n 与n−1 做与运算，直到 n 变为 0 即可。因为每次运算会使得 n 的最低位的 1 被翻转，因此运算次数就等于 n 的二进制位中 1 的个数。
         *
         * 复杂度分析：
         * 时间复杂度：O(logn)。循环次数等于 n 的二进制位中 1 的个数，最坏情况下 n 的二进制位全部为 1。我们需要循环 logn 次。
         * 空间复杂度：O(1)，我们只需要常数的空间保存若干变量。
         */
        public int hammingWeight(int n) {
            int result = 0;
            while(n != 0){
                n = n & (n-1);
                result++;
            }
            return result;
        }
    }

}
