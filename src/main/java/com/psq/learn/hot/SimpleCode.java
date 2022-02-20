package com.psq.learn.hot;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @Description 简单程度
 * @Author psq
 * @Date 2022/2/15 16:59
 */
public class SimpleCode {

    public static void main(String[] args) {
        System.out.println("Hello leetcode!");
    }

    /**
     * 两数之和
     * 给定一个整数数组nums和一个整数目标值target，请你在该数组中找出和为目标值target的那两个整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * 你可以按任意顺序返回答案。
     */
    class day1 {
        /**
         * 方法一：暴力枚举
         * 解题思路：
         * 最容易想到的方法是枚举数组中的每一个数 x，寻找数组中是否存在 target - x。
         * 当我们使用遍历整个数组的方式寻找 target - x 时，需要注意到每一个位于 x 之前的元素都已经和 x 匹配过，因此不需要再进行匹配。而每一个元素不能被使用两次，所以我们只需要在 x 后面的元素中寻找 target - x。
         *
         * 复杂度分析：
         * 时间复杂度：O(N^2)，其中 NN 是数组中的元素数量。最坏情况下数组中任意两个数都要被匹配一次。
         * 空间复杂度：O(1)。
         */
        public int[] twoSum1(int[] nums, int target) {
            int sum = nums[0] + nums[1];
            if(sum == target){
                return new int[0];
            }
            int length = nums.length;
            for(int i=0; i<length; i++){
                int a = nums[i];
                for(int j=i+1; j<length; j++){
                    int b = nums[j];
                    sum = a + b;
                    if(sum == target){
                        return new int[]{i,j};
                    }
                }
            }
            return new int[0];
        }

        /**
         * 方法二：哈希表,空间换时间；时间复杂度O(N),空间复杂度O(N)
         * 解题思路：
         * 注意到方法一的时间复杂度较高的原因是寻找 target - x 的时间复杂度过高。因此，我们需要一种更优秀的方法，能够快速寻找数组中是否存在目标元素。如果存在，我们需要找出它的索引。
         * 使用哈希表，可以将寻找 target - x 的时间复杂度降低到从 O(N) 降低到 O(1)。
         * 这样我们创建一个哈希表，对于每一个 x，我们首先查询哈希表中是否存在 target - x，然后将 x 插入到哈希表中，即可保证不会让 x 和自己匹配
         *
         * 复杂度分析：
         * 时间复杂度：O(N)，其中 N 是数组中的元素数量。对于每一个元素 x，我们可以 O(1) 地寻找 target - x。
         * 空间复杂度：O(N)，其中 N 是数组中的元素数量。主要为哈希表的开销
         */
        class twoSum2 {
            public int[] twoSum(int[] nums, int target) {
                int sum = nums[0] + nums[1];
                if(sum == target){
                    return new int[0];
                }
                HashMap<Integer, Integer> numIndex = new HashMap<Integer, Integer>();
                for (int i=0; i<nums.length; i++){
                    if (numIndex.containsKey(target - nums[i])){
                        return new int[]{i, numIndex.get(target - nums[i])};
                    }
                    numIndex.put(nums[i], i);
                }
                return new int[0];
            }
        }
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

    /**
     * 有效的括号
     * 题目：给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     */
    class day4{
        /**
         *  解题思路：
         *  判断括号的有效性可以使用「栈」这一数据结构来解决。
         *  我们遍历给定的字符串 s。当我们遇到一个左括号时，我们会期望在后续的遍历中，有一个相同类型的右括号将其闭合。由于后遇到的左括号要先闭合，因此我们可以将这个左括号放入栈顶。
         *  当我们遇到一个右括号时，我们需要将一个相同类型的左括号闭合。此时，我们可以取出栈顶的左括号并判断它们是否是相同类型的括号。如果不是相同的类型，或者栈中并没有左括号，那么字符串 s 无效，返回 False。为了快速判断括号的类型，我们可以使用哈希表存储每一种括号。哈希表的键为右括号，值为相同类型的左括号。
         *  在遍历结束后，如果栈中没有左括号，说明我们将字符串 s 中的所有左括号闭合，返回 True，否则返回 False。
         *  注意到有效字符串的长度一定为偶数，因此如果字符串的长度为奇数，我们可以直接返回 False，省去后续的遍历判断过程。
         *
         *  复杂度分析：
         *  时间复杂度：O(n)，其中 nn 是字符串 s 的长度。
         *  空间复杂度：O(n +∣Σ∣)，其中  表示字符集，本题中字符串只包含 66 种括号，∣Σ∣=6。栈中的字符数量为 O(n)，而哈希表使用的空间为 O(∣Σ∣)，相加即可得到总空间复杂度。
         */
        public boolean isValid(String s) {
            int length = s.length();
            if(length % 2 == 1){
                return false;
            }
            HashMap<Character, Character> map = new HashMap<>();
            map.put('}', '{');
            map.put(')', '(');
            map.put(']', '[');
            //栈：先进后出；peek()获取栈顶元素但不取出，而pop()是直接取出栈顶元素
            Deque<Character> deque =  new LinkedList<Character>();
            for(int i=0; i<length; i++){
                Character str = s.charAt(i);
                if(map.containsKey(str)){
                    if(deque.size() == 0 || deque.peek() != map.get(str)){
                        return false;
                    }
                    deque.pop();
                }else{
                    deque.push(str);
                }
            }
            return deque.isEmpty();
        }
    }
}
