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
         * 此类求 多少种可能性 的题目一般都有 递推性质 ，即 f(n) 和 f(n-1)…f(1) 之间是有联系的。
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

    /**
     * 反转链表
     * 题目：定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
     * 示例:
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     */
    class day23{
        public class ListNode {
            int val;
            ListNode next;
            ListNode(int x) { val = x; }
        }

        /**
         * 方法一：迭代(双指针)
         * 假设链表为 1→2→3→∅，我们想要把它改成 3→2→1→∅。
         * 在遍历链表时，将当前节点的next 指针改为指向前一个节点。由于节点没有引用其前一个节点，因此必须事先存储其前一个节点。在更改引用之前，还需要存储后一个节点。最后返回新的头引用
         */
        public ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;
            while (curr != null) {
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            return prev;
        }
    }

    /**
     * 合并两个排序的链表
     * 题目：输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的
     */
    class day24{
        public class ListNode {
            int val;
            ListNode next;
            ListNode(int x) { val = x; }
        }

        /**
         *  解题思路：
         * 1.初始化：伪头节点 dum ，节点 cur 指向 dum
         * 2.循环合并：当l1或l2为空时跳出
         *  当l1.val<l2.val 时： cur 的后继节点指定为 l1，并 l1向前走一步
         *  当l1.val≥l2.val 时： cur 的后继节点指定为 l2，并l2向前走一步
         *  然后节点 cur 向前走一步，即cur=cur.next
         * 3.合并剩余尾部： 跳出时有两种情况，即 l1为空 或 l2为空
         *  若l1=null ： 将 l1添加至节点 cur之后；
         *  若l2=null ： 将 l2添加至节点 cur之后；
         * 4.返回值： 合并链表在伪头节点 dum 之后，因此返回 dum.next 即可
         *
         *复杂度分析：
         * 时间复杂度 O(M+N)：M,N 分别为链表 l1 , l2的长度，合并操作需遍历两链表。
         * 空间复杂度 O(1) ： 节点引用 dum , cur 使用常数大小的额外空间
         */
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode cur = new ListNode(-1);
            ListNode temp = cur;
            while(l1 != null && l2 != null){
                if(l1.val <= l2.val){
                    temp.next = new ListNode(l1.val);
                    l1 = l1.next;
                }else{
                    temp.next = new ListNode(l2.val);
                    l2 = l2.next;
                }
                temp = temp.next;
            }
            temp.next = l1 == null ?l2:l1;
            return cur.next;
        }
    }

    /**
     * 找出数组中的所有 K 近邻下标
     * 题目：给你一个下标从 0 开始的整数数组 nums 和两个整数 key 和 k 。K 近邻下标 是 nums 中的一个下标 i ，并满足至少存在一个下标 j 使得 |i - j| <= k 且 nums[j] == key 。
     * 以列表形式返回按 递增顺序 排序的所有 K 近邻下标
     */
    class day26{
        /**
         * 方法一：自己写的，思维推导，有待优化
         * 复杂度分析：
         * 时间复杂度：虽然 for 循环里还有 while 循环，但是每个位置最多被访问两次（快慢指针各访问一次），所以整体的时间复杂度为 O(N)
         * 空间复杂度：O(1)
         */
        public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == key) {
                    find(i,k,nums.length,result);
                }
            }
            return result;
        }

        private void find(int i, int k, int length, List<Integer> result) {
            int start = i-k < 0?0:i-k;
            int end = i+k >= length? length-1:i+k;
            if(result.size() >0){
                start = result.get(result.size()-1);
            }
            for(; start<=end; start++){
                if(Math.abs(i - start) <= k && !result.contains(new Integer(start))){
                    result.add(start);
                }
            }
        }
    }

    /**
     * 二叉树的镜像
     * 题目：请完成一个函数，输入一个二叉树，该函数输出它的镜像
     *
     */
    class day27{
        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            TreeNode(int x) { val = x; }
        }

        /**
         * 方法一：递归
         * 思路与算法
         * 这是一道很经典的二叉树问题。显然，我们从根节点开始，递归地对树进行遍历，并从叶子节点先开始翻转得到镜像。
         * 如果当前遍历到的节点 root 的左右两棵子树都已经翻转得到镜像，那么我们只需要交换两棵子树的位置，即可得到以root 为根节点的整棵子树的镜像
         *
         * 复杂度分析
         * 时间复杂度：O(N)，其中 N 为二叉树节点的数目。我们会遍历二叉树中的每一个节点，对每个节点而言，我们在常数时间内交换其两棵子树。
         * 空间复杂度：O(N)。使用的空间由递归栈的深度决定，它等于当前节点在二叉树中的高度。在平均情况下，二叉树的高度与节点个数为对数关系，即 O(logN)。而在最坏情况下，树形成链状，空间复杂度为O(N)
         */
        public TreeNode mirrorTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            TreeNode left = mirrorTree(root.left);
            TreeNode right = mirrorTree(root.right);
            root.left = right;
            root.right = left;
            return root;
        }
    }

    /**
     * 对称的二叉树
     * 题目：请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的
     */
    class day28{
        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            TreeNode(int x) { val = x; }
        }

        /**
         * 解题思路：
         * 对称二叉树定义： 对于树中 任意两个对称节点 L 和 R ，一定有：
         * L.val = R.valL：即此两对称节点值相等。
         * L.left.val = R.right.val ：即 L 的 左子节点 和 R 的 右子节点 对称；
         * L.right.val = R.left.val ：即 L 的 右子节点 和 R 的 左子节点 对
         *
         * 算法流程：
         * 特例处理： 若根节点 root 为空，则直接返回 true 。
         * 返回值： 即 recur(root.left, root.right) ;
         *
         * recur(L, R) ：
         * 终止条件：
         * 当 L 和 R 同时越过叶节点： 此树从顶至底的节点都对称，因此返回 true ；
         * 当 L 或 R 中只有一个越过叶节点： 此树不对称，因此返回 false ；
         * 当节点 L 值 != 节点 R 值： 此树不对称，因此返回 false ；
         * 递推工作：
         * 判断两节点 L.left 和 R.right 是否对称，即 recur(L.left, R.right) ；
         * 判断两节点 L.right 和 R.left 是否对称，即 recur(L.right, R.left) ；
         * 返回值： 两对节点都对称时，才是对称树，因此用与逻辑符 && 连接
         *
         * 复杂度分析：
         * 时间复杂度 O(N) ： 其中 N 为二叉树的节点数量，每次执行 recur() 可以判断一对节点是否对称，因此最多调用 N/2次 recur() 方法。
         * 空间复杂度 O(N) ： 最差情况下（见下图），二叉树退化为链表，系统使用 O(N) 大小的栈空间。
         */
        public boolean isSymmetric(TreeNode root) {
            return root == null ? true : recur(root.left, root.right);
        }
        boolean recur(TreeNode L, TreeNode R) {
            if(L == null && R == null) return true;
            if(L == null || R == null || L.val != R.val) return false;
            return recur(L.left, R.right) && recur(L.right, R.left);
        }
    }

    /**
     * 顺时针打印矩阵
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字
     */
    class day29{
        /**
         * 方法一：模拟
         * 可以模拟打印矩阵的路径。初始位置是矩阵的左上角，初始方向是向右，当路径超出界限或者进入之前访问过的位置时，顺时针旋转，进入下一个方向。
         * 判断路径是否进入之前访问过的位置需要使用一个与输入矩阵大小相同的辅助矩阵 visited，其中的每个元素表示该位置是否被访问过。当一个元素被访问时，将 visited 中的对应位置的元素设为已访问。
         * 如何判断路径是否结束？由于矩阵中的每个元素都被访问一次，因此路径的长度即为矩阵中的元素数量，当路径的长度达到矩阵中的元素数量时即为完整路径，将该路径返回
         *
         * 复杂度分析
         * 时间复杂度：O(mn)，其中 m 和 n 分别是输入矩阵的行数和列数。矩阵中的每个元素都要被访问一次。
         * 空间复杂度：O(mn)。需要创建一个大小为m×n 的矩阵 visited 记录每个位置是否被访问过
         */
        public int[] spiralOrder(int[][] matrix) {
            if(null == matrix || 0 == matrix.length || 0 == matrix[0].length){
                return new int[0];
            }
            int rows = matrix.length;
            int colums = matrix[0].length;
            boolean[][] visted = new boolean[rows][colums];

            int total = rows*colums;
            int[] result = new int[total];

            int row = 0, colum = 0;
            int[][] indexs = {{0,1},{1,0},{0,-1},{-1,0}};
            int index = 0;
            for(int i=0; i<total; i++){
                result[i] = matrix[row][colum];
                visted[row][colum] = true;
                int nextRow = row + indexs[index][0], nextColum = colum + indexs[index][1];
                if(nextRow < 0 || nextRow >= rows || nextColum < 0 || nextColum >= colums || visted[nextRow][nextColum]){
                    index = (index + 1)%4;
                }
                row += indexs[index][0];
                colum += indexs[index][1];
            }
            return result;
        }

        public int[] spiralOrder2(int[][] matrix) {
            if(matrix.length == 0) return new int[0];
            int l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1, x = 0;
            int[] res = new int[(r + 1) * (b + 1)];
            while(true) {
                for(int i = l; i <= r; i++) res[x++] = matrix[t][i]; // left to right.
                if(++t > b) break;
                for(int i = t; i <= b; i++) res[x++] = matrix[i][r]; // top to bottom.
                if(l > --r) break;
                for(int i = r; i >= l; i--) res[x++] = matrix[b][i]; // right to left.
                if(t > --b) break;
                for(int i = b; i >= t; i--) res[x++] = matrix[i][l]; // bottom to top.
                if(++l > r) break;
            }
            return res;
        }
    }

    /**
     * 包含min函数的栈
     * 题目：定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)
     */
    class day30{
        /**
         * 方法一：双栈，辅助栈(优解)
         * 将 min() 函数复杂度降为 O(1) ，可通过建立辅助栈实现；
         * 数据栈 A ： 栈 A 用于存储所有元素，保证入栈 push() 函数、出栈 pop() 函数、获取栈顶 top() 函数的正常逻辑。
         * 辅助栈 B ： 栈 B 中存储栈 A 中所有 非严格降序 的元素，则栈 A 中的最小元素始终对应栈 B 的栈顶元素，即 min() 函数只需返回栈 B 的栈顶元素即可
         *
         *复杂度分析：
         * 时间复杂度 O(1) ： push(), pop(), top(), min() 四个函数的时间复杂度均为常数级别。
         * 空间复杂度 O(N) ： 当共有 N个待入栈元素时，辅助栈 B 最差情况下存储 N 个元素，使用 O(N) 额外空间
         *
         * 方法二：栈+集合，每次添加元素，加入list中，并排序，使其有序，每次pop()，删除list.remove(元素)
         * 能解答这道题，空间复杂度和方法一差不多，但时间效率低
         */
        class MinStack {
            Stack<Integer> initial, temp = null;
            public MinStack() {
                initial = new Stack<>();
                temp = new Stack<>();
            }

            public void push(int x) {
                initial.push(x);
                if(temp.empty() || temp.peek() >= x){
                    temp.push(x);
                }
            }

            public void pop() {
                if(initial.pop().equals(temp.peek())){
                    temp.pop();
                }
            }

            public int top() {
                return initial.peek();
            }

            public int min() {
                return temp.peek();
            }
        }
    }

    /**
     * 从上到下打印二叉树 II
     * 题目：从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行
     */
    class day31 {
        /**
         * 解题思路：
         * 使用队列，先进先出
         *
         * 复杂度分析：
         * 时间复杂度 O(N)： N 为二叉树的节点数量，即 BFS 需循环 N 次。
         * 空间复杂度 O(N)： 最差情况下，即当树为平衡二叉树时，最多有 N/2 个树节点同时在 queue 中，使用 O(N) 大小的额外空间。
         *
         */
        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            TreeNode(int x) { val = x; }
        }

        public List<List<Integer>> levelOrder(TreeNode root) {
            Queue<TreeNode> queue = new LinkedList<>();
            List<List<Integer>> result = new ArrayList<>();
            if (null != root) {
                queue.add(root);
            }
            while (!queue.isEmpty()) {
                List<Integer> temp = new ArrayList<>();
                for (int i = queue.size(); i > 0; i--) {
                    TreeNode node = queue.poll();
                    temp.add(node.val);
                    if (null != node.left) {
                        queue.add(node.left);
                    }
                    if (null != node.right) {
                        queue.add(node.right);
                    }
                }
                result.add(temp);
            }
            return result;
        }
    }

    /**
     * 连续子数组的最大和
     * 题目：输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。要求时间复杂度为O(n)
     */
    class day38{
        /**
         * 方法：动态规划
         *假设nums 数组的长度是 n，下标从 0 到 n-1。
         * 我们用 f(i) 代表以第 i 个数结尾的「连续子数组的最大和」，那么很显然我们要求的答案就是：
         * max{ f(i) }
         * 0≤i≤n−1
         * 因此我们只需要求出每个位置的 f(i)，然后返回 f 数组中的最大值即可。那么我们如何求 f(i) 呢？我们可以考虑nums[i] 单独成为一段还是加入 f(i-1) 对应的那一段，这取决于 nums[i] 和 f(i-1) +nums[i] 的大小，我们希望获得一个比较大的，于是可以写出这样的动态规划转移方程：
         * f(i)=max{f(i−1)+nums[i],nums[i]}
         * 不难给出一个时间复杂度 O(n)、空间复杂度 O(n)的实现，即用一个 f 数组来保存 f(i) 的值，用一个循环求出所有 f(i)。考虑到 f(i) 只和 f(i-1) 相关，于是我们可以只用一个变量 pre 来维护对于当前 f(i) 的 f(i-1) 的值是多少，从而让空间复杂度降低到 O(1)，这有点类似「滚动数组」的思想。
         *
         * 复杂度
         * 时间复杂度：O(n)，其中 n 为nums 数组的长度。我们只需要遍历一遍数组即可求得答案。
         * 空间复杂度：O(1)。我们只需要常数空间存放若干变量
         */
        public int maxSubArray(int[] nums) {
            int result =  nums[0];
            for(int i=1; i<nums.length; i++){
                nums[i] += Math.max(nums[i-1], 0);
                result = Math.max(result, nums[i]);
            }
            return result;
        }
    }

    /**
     * 第一个只出现一次的字符
     * 题目：在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
     */
    class day41{
        class Solution {
            /**
             * 解题思路
             * 方法二：使用哈希表存储索引
             * 思路与算法
             * 我们可以对方法一进行修改，使得第二次遍历的对象从字符串变为哈希映射。
             * 具体地，对于哈希映射中的每一个键值对，键表示一个字符，值表示它的首次出现的索引（如果该字符只出现一次）或者 -1（如果该字符出现多次）。当我们第一次遍历字符串时，设当前遍历到的字符为 c，如果 c 不在哈希映射中，我们就将 c 与它的索引作为一个键值对加入哈希映射中，否则我们将 c 在哈希映射中对应的值修改为 −1。
             * 在第一次遍历结束后，我们只需要再遍历一次哈希映射中的所有值，找出其中不为 -1 的最小值，即为第一个不重复字符的索引，然后返回该索引对应的字符。如果哈希映射中的所有值均为 -1，我们就返回空格。
             *
             * 复杂度分析
             * 时间复杂度：O(n)，其中 n 是字符串 s 的长度。第一次遍历字符串的时间复杂度为 O(n)，第二次遍历哈希映射的时间复杂度为 O(∣Σ∣)，由于 s 包含的字符种类数一定小于 s 的长度，因此 O(∣Σ∣) 在渐进意义下小于 O(n)，可以忽略。
             * 空间复杂度：O(∣Σ∣)，其中Σ 是字符集，在本题中 s 只包含小写字母，因此 ∣Σ∣≤26。我们需要 O(∣Σ∣) 的空间存储哈希映射。
             */
            public char firstUniqChar(String s) {
                Map<Character, Integer> position = new HashMap<Character, Integer>();
                int n = s.length();
                for (int i = 0; i < n; ++i) {
                    char ch = s.charAt(i);
                    if (position.containsKey(ch)) {
                        position.put(ch, -1);
                    } else {
                        position.put(ch, i);
                    }
                }
                int first = n;
                for (Map.Entry<Character, Integer> entry : position.entrySet()) {
                    int pos = entry.getValue();
                    if (pos != -1 && pos < first) {
                        first = pos;
                    }
                }
                return first == n ? ' ' : s.charAt(first);
            }
        }
    }

    /**
     * 查找0～n-1中缺失的数字
     * 题目：一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字
     */
    class day43{
        /**
         * 解题思路：有序数组用二分查找
         * 初始化： 左边界i=0 ，右边界 j = len(nums) - 1j ；代表闭区间 [i,j] 。
         * 循环二分： 当 i≤j 时循环 （即当闭区间 [i, j]为空时跳出） ；
         * 计算中点 m = (i + j) / 2，其中 "/" 为向下取整除法；
         * 若 nums[m] = m，则 “右子数组的首位元素” 一定在闭区间[m+1,j] 中，因此执行 i=m+1；
         * 若 nums[m] =m ，则 “左子数组的末位元素” 一定在闭区间 [i,m−1] 中，因此执行 j=m−1；
         * 返回值： 跳出时，变量 i 和 j 分别指向 “右子数组的首位元素” 和 “左子数组的末位元素” 。因此返回 i 即可
         * 复杂度分析：
         * 时间复杂度 O(logN)： 二分法为对数级别复杂度。
         * 空间复杂度 O(1)： 几个变量使用常数大小的额外空间
         */
        public int missingNumber(int[] nums) {
            int i=0,j=nums.length-1;
            while(i<=j){
                int num = (i+j)/2;
                if(nums[num] == num){
                    i=num+1;
                }else{
                    j=num-1;
                }
            }
            return i;
        }
    }

    /**
     * 二叉树的深度
     * 题目：输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度
     */
    class day44{
        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            TreeNode(int x) { val = x; }
        }

        /**
         * 求树的深度需要遍历树的所有节点，基于 后序遍历（DFS）和层序遍历（BFS）的两种解法
         */
        public int maxDepth(TreeNode root) {
            if(null == root){
                return 0;
            }
            int result = 1;
            result+= Math.max(maxDepth(root.left),maxDepth(root.right));
            return result;
        }
    }

    /**
     * 平衡二叉树
     * 题目：输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树
     */
    class day46{
        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            TreeNode(int x) { val = x; }
        }

        /**
         * 解题思路：
         * 自底向上递归的做法类似于后序遍历，对于当前遍历到的节点，先递归地判断其左右子树是否平衡，
         * 再判断以当前节点为根的子树是否平衡。如果一棵子树是平衡的，则返回其高度（高度一定是非负整数），
         * 否则返回 −1。如果存在一棵子树不平衡，则整个二叉树一定不平衡
         *
         * 复杂度分析
         * 时间复杂度：O(n)，其中 n 是二叉树中的节点个数。使用自底向上的递归，每个节点的计算高度和判断是否平衡都只需要处理一次，最坏情况下需要遍历二叉树中的所有节点，因此时间复杂度是 O(n)。
         * 空间复杂度：O(n)，其中 n 是二叉树中的节点个数。空间复杂度主要取决于递归调用的层数，递归调用的层数不会超过 n
         */
        public boolean isBalanced(TreeNode root) {
            return height(root) >= 0;
        }

        public int height(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftHeight = height(root.left);
            int rightHeight = height(root.right);
            if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
                return -1;
            } else {
                return Math.max(leftHeight, rightHeight) + 1;
            }
        }
    }
}