package com.psq.learn.offer;

import java.util.*;

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

    /**
     * 打印从1到最大的n位数
     * 题目：输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999
     */
    class day18{
        /**
         * 解题思路：
         * 最大的 n位数（记为end ）和位数 n 的关系： 例如最大的 1 位数是 9 ，最大的 2 位数是 99 ，最大的 3 位数是 999 。则可推出公式：
         * end = 10^n - 1
         * 大数越界问题： 当 nn 较大时，end 会超出 int32 整型的取值范围，超出取值范围的数字无法正常存储。但由于本题要求返回 int 类型数组，相当于默认所有数字都在 int32 整型取值范围内，因此不考虑大数越界问题。
         * 因此，只需定义区间 [1, 10^n - 1]和步长 1 ，通过 for 循环生成结果列表 res 并返回即可
         *
         * 复杂度分析：
         * 时间复杂度 O(10^n) ： 生成长度为 10^n的列表需使用 O(10^n)时间。
         * 空间复杂度 O(1) ： 建立列表需使用 (1) 大小的额外空间（ 列表作为返回结果，不计入额外空间 ）。
         */
        public int[] printNumbers(int n) {
            int end = (int)Math.pow(10, n) - 1;
            int[] res = new int[end];
            for(int i = 0; i < end; i++){
                res[i] = i + 1;
            }
            return res;
        }
    }

    /**
     * Excel 表中某个范围内的单元格
     * 题目：Excel 表中的一个单元格 (r, c) 会以字符串 "<col><row>" 的形式进行表示，其中：
     *
     * <col> 即单元格的列号 c 。用英文字母表中的 字母 标识。
     * 例如，第 1 列用 'A' 表示，第 2 列用 'B' 表示，第 3 列用 'C' 表示，以此类推。
     * <row> 即单元格的行号 r 。第 r 行就用 整数 r 标识。
     * 给你一个格式为 "<col1><row1>:<col2><row2>" 的字符串 s ，其中 <col1> 表示 c1 列，<row1> 表示 r1 行，<col2> 表示 c2 列，<row2> 表示 r2 行，并满足 r1 <= r2 且 c1 <= c2 。
     *
     * 找出所有满足 r1 <= x <= r2 且 c1 <= y <= c2 的单元格，并以列表形式返回。单元格应该按前面描述的格式用 字符串 表示，并以 非递减 顺序排列（先按列排，再按行排）
     */
    class day19{
        /**
         * 数学推导即可
         */
        public List<String> cellsInRange(String s) {
            ArrayList<String> result = new ArrayList<>();
            String[] array = s.split(":");
            char[] a = array[0].toCharArray();
            char[] b = array[1].toCharArray();
            int c1 = (int)a[0];
            int c2 = (int)b[0];
            //字母转数字：减去字符'0'
            int r1 = a[1] - '0';
            int r2 = b[1] - '0';
            for(int i=c1; i<=c2; i++){
                String col = String.valueOf((char)(i));
                for(int j=r1; j<=r2; j++){
                    result.add(col + j);
                }
            }
            return result;
        }
    }

    /**
     *  删除链表的节点
     *  题目：给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。返回删除后的链表的头节点
     */
    class day20{
        public class ListNode {
            int val;
            ListNode next;
            ListNode(int x) { val = x; }
        }

        /**
         * 解题思路：
         * 特例处理： 当应删除头节点 head 时，直接返回 head.next 即可。
         * 初始化： pre = head , cur = head.next 。
         * 定位节点： 当 cur 为空 或 cur 节点值等于 val 时跳出。
         * 保存当前节点索引，即 pre = cur 。
         * 遍历下一节点，即 cur = cur.next 。
         * 删除节点： 若 cur 指向某节点，则执行 pre.next = cur.next ；若 cur 指向 nullnull ，代表链表中不包含值为 val 的节点。
         * 返回值： 返回链表头部节点 head 即可
         *
         * 复杂度分析：
         * 时间复杂度 O(N)： N 为链表长度，删除操作平均需循环 N/2 次，最差 N 次。
         * 空间复杂度 O(1)： cur, pre 占用常数大小额外空间。
         */
        public ListNode deleteNode(ListNode head, int val) {
            if(head.val == val) {
                return head.next;
            }
            ListNode pre = head, cur = head.next;
            while(cur != null && cur.val != val) {
                pre = cur;
                cur = cur.next;
            }
            if(cur != null) {
                pre.next = cur.next;
            }
            return head;
        }
    }

    /**
     * 调整数组顺序使奇数位于偶数前面
     * 题目：输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。
     */
    class day21{
        /**
         * 解题思路：
         * 算法流程：
         * 初始化： i , j 双指针，分别指向数组 nums 左右两端；
         * 循环交换： 当 i = j时跳出；
         * 指针 i 遇到奇数则执行 i = i + 1跳过，直到找到偶数；
         * 指针 j 遇到偶数则执行 j = j - 1 跳过，直到找到奇数；
         * 交换 nums[i] 和 nums[j] 值；
         * 返回值： 返回已修改的 nums 数组
         *
         *复杂度分析：
         * 时间复杂度 O(N) ： N 为数组 nums 长度，双指针 i, j 共同遍历整个数组。
         * 空间复杂度 O(1) ： 双指针 i, j 使用常数大小的额外空间
         */
        public int[] exchange(int[] nums) {
            int i = 0, j = nums.length - 1, tmp;
            while(i < j) {
                while(i < j && (nums[i] & 1) == 1) {
                    i++;
                }
                while(i < j && (nums[j] & 1) == 0) {
                    j--;
                }
                tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
            return nums;
        }
    }

    /**
     * 链表中倒数第k个节点
     * 题目：
     * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
     * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点
     * 示例：
     * 给定一个链表: 1->2->3->4->5, 和 k = 2.
     * 返回链表 4->5
     */
    class day22{
        public class ListNode {
            int val;
            ListNode next;
            ListNode(int x) { val = x; }
        }

        /**
         * 方法一：顺序查找
         * 思路与算法:
         * 最简单直接的方法即为顺序查找，假设当前链表的长度为 n，则我们知道链表的倒数第 k 个节点即为正数第 n - k个节点，此时我们只需要顺序遍历到链表的第 n - k个节点即为倒数第 k 个节点。
         * 我们首先求出链表的长度 n，然后顺序遍历到链表的第 n - k 个节点返回即可
         *
         * 复杂度分析
         * 时间复杂度：O(n)，其中 n 为链表的长度。需要两次遍历。
         * 空间复杂度：O(1)。
         */
        public ListNode getKthFromEnd1(ListNode head, int k) {
            HashMap<Integer, Integer> result = new HashMap<>();
            Integer num = 0;
            ListNode temp = head;
            while(null != temp){
                num++;
                result.put(num, temp.val);
                temp = temp.next;
            }
            num = num - k;
            k = 0;
            while(k != num){
                head = head.next;
                k++;
            }
            return head;
        }

        /**
         * 方法二：双指针
         * 思路与算法
         * 快慢指针的思想。我们将第一个指针 fast 指向链表的第 k + 1 个节点，第二个指针 slow 指向链表的第一个节点，此时指针 fast 与 slow 二者之间刚好间隔 k 个节点。此时两个指针同步向后走，当第一个指针 fast 走到链表的尾部空节点时，则此时 slow 指针刚好指向链表的倒数第k个节点。
         * 先将 fast 指向链表的头节点，然后向后走 k 步，则此时 fast 指针刚好指向链表的第 k+1 个节点。
         * 再将slow 指向链表的头节点，同时 slow 与 fast 同步向后走，当 fast 指针指向链表的尾部空节点时，则此时返回slow 所指向的节点即可。
         *
         * 复杂度分析
         * 时间复杂度：O(n)，其中 n 为链表的长度。我们使用快慢指针，只需要一次遍历即可，复杂度为 O(n)。
         * 空间复杂度：O(1)。
         */
        public ListNode getKthFromEnd2(ListNode head, int k) {
            ListNode fast = head;
            ListNode slow = head;

            while (fast != null && k > 0) {
                fast = fast.next;
                k--;
            }
            while (fast != null) {
                fast = fast.next;
                slow = slow.next;
            }

            return slow;
        }
    }
}
