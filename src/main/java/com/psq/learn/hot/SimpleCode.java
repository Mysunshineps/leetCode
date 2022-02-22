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
        System.out.println("Hello hot-SimpleCode!");
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
                    if(deque.size() == 0 || deque.peek().equals(map.get(str))){
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

    /**
     * 合并两个有序链表
     * 题目：
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的
     */
    class day5{
        public class ListNode {
            int val;
            ListNode next;
            ListNode() {}
            ListNode(int val) { this.val = val; }
            ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        }

        /**
         * 方法一：递归，将两个链表头部值较小的一个节点与剩下元素的 merge 操作结果合并
         * 解题思路：
         * 如果 l1 或者 l2 一开始就是空链表 ，那么没有任何操作需要合并，所以我们只需要返回非空链表。否则，我们要判断 l1 和 l2 哪一个链表的头节点的值更小，然后递归地决定下一个添加到结果里的节点。如果两个链表有一个为空，递归结束。
         *
         * 复杂度分析：
         * 时间复杂度：O(n + m)，其中 n 和 m分别为两个链表的长度。因为每次调用递归都会去掉 l1 或者 l2 的头节点（直到至少有一个链表为空），函数 mergeTwoList 至多只会递归调用每个节点一次。因此，时间复杂度取决于合并后的链表长度，即 O(n+m)。
         * 空间复杂度：O(n + m)，其中 n 和 m分别为两个链表的长度。递归调用 mergeTwoLists 函数时需要消耗栈空间，栈空间的大小取决于递归调用的深度。结束递归调用时 mergeTwoLists 函数最多调用 n+m 次，因此空间复杂度为 O(n+m)O。
         */
        public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            } else if (l2 == null) {
                return l1;
            } else if (l1.val < l2.val) {
                l1.next = mergeTwoLists1(l1.next, l2);
                return l1;
            } else {
                l2.next = mergeTwoLists1(l1, l2.next);
                return l2;
            }
        }

        /**
         * 方法二：迭代
         * 解题思路：
         * 当 l1 和 l2 都不是空链表时，判断 l1 和 l2 哪一个链表的头节点的值更小，将较小值的节点添加到结果里，当一个节点被添加到结果里之后，将对应链表中的节点向后移一位
         *  首先，我们设定一个哨兵节点 prehead ，这可以在最后让我们比较容易地返回合并后的链表。我们维护一个 prev 指针，我们需要做的是调整它的 next 指针。然后，我们重复以下过程，直到 l1 或者 l2 指向了 null ：如果 l1 当前节点的值小于等于 l2 ，我们就把 l1 当前的节点接在 prev 节点的后面同时将 l1 指针往后移一位。否则，我们对 l2 做同样的操作。不管我们将哪一个元素接在了后面，我们都需要把 prev 向后移一位。
         *  在循环终止的时候， l1 和 l2 至多有一个是非空的。由于输入的两个链表都是有序的，所以不管哪个链表是非空的，它包含的所有元素都比前面已经合并链表中的所有元素都要大。这意味着我们只需要简单地将非空链表接在合并链表的后面，并返回合并链表即可
         *
         * 复杂度分析：
         * 时间复杂度：O(n + m)，其中 n 和 m 分别为两个链表的长度。因为每次循环迭代中，l1 和 l2 只有一个元素会被放进合并链表中， 因此 while 循环的次数不会超过两个链表的长度之和。所有其他操作的时间复杂度都是常数级别的，因此总的时间复杂度为 O(n+m)。
         * 空间复杂度：O(1)。我们只需要常数的空间存放若干变量。
         */
        public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
            ListNode result = new ListNode(-1);
            ListNode resultTemp = result;
            while(null != list1 && null != list2){
                if(list1.val < list2.val){
                    resultTemp.next = list1;
                    list1 = list1.next;
                }else{
                    resultTemp.next = list2;
                    list2 = list2.next;
                }
                resultTemp = resultTemp.next;
            }
            resultTemp.next = list1 == null ? list2:list1;
            return result.next;
        }
    }
}
