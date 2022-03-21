package com.psq.learn.week;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 周赛-简单程度
 * @Author psq
 * @Date 2022/3/20 16:59
 */
public class SimpleCode {

    /**
     * 统计数组中峰和谷的数量
     * 题目：给你一个下标从 0 开始的整数数组 nums 。如果两侧距 i 最近的不相等邻居的值均小于 nums[i] ，则下标 i 是 nums 中，某个峰的一部分。类似地，如果两侧距 i 最近的不相等邻居的值均大于 nums[i] ，则下标 i 是 nums 中某个谷的一部分。对于相邻下标 i 和 j ，如果 nums[i] == nums[j] ， 则认为这两下标属于 同一个 峰或谷。
     * 注意，要使某个下标所做峰或谷的一部分，那么它左右两侧必须 都 存在不相等邻居。
     * 返回 nums 中峰和谷的数量
     */
    class week285{
        /**
         * 解题思路：
         * flag = 2表示前面在上升， flag=1表示前面在下降
         * 所以当flag从1变到2，或者从2变到1，就是波峰波谷
         */
        public int countHillValley(int[] nums) {
            if(null == nums || nums.length <= 1){
                return 0;
            }
            int flag = 0;
            int result = 0;
            for(int i=1; i<nums.length; i++){
                if(nums[i] > nums[i-1]){
                    if(flag == 1){
                        result++;
                    }
                    flag = 2;
                }else if(nums[i] < nums[i-1]){
                    if(flag == 2){
                        result++;
                    }
                    flag = 1;
                }
            }
            return result;
        }
    }

    class doubleWeek_74{
        /**
         * 将数组划分成相等数对
         * 题目：
         * 给你一个整数数组 nums ，它包含 2 * n 个整数。
         * 你需要将 nums 划分成 n 个数对，满足：
         * 每个元素 只属于一个 数对。
         * 同一数对中的元素 相等。
         * 如果可以将 nums 划分成 n 个数对，请你返回 true ，否则返回 false
         */
        public boolean divideArray(int[] nums) {
            if(null == nums || nums.length%2 == 1){
                return false;
            }

            Map<Integer, Integer> map = new HashMap<>();
            for(int i=0; i<nums.length; i++){
                if(map.containsKey(nums[i])){
                    map.put(nums[i], map.get(nums[i]) + 1);
                }else{
                    map.put(nums[i], 1);
                }
            }

            for(Integer num:map.values()){
                if(num % 2 == 1){
                    return false;
                }
            }
            return true;
        }

        /**
         *  字符串中最多数目的子字符串
         *  题目：
         *  给你一个下标从 0 开始的字符串 text 和另一个下标从 0 开始且长度为 2 的字符串 pattern ，两者都只包含小写英文字母。
         * 你可以在 text 中任意位置插入 一个 字符，这个插入的字符必须是 pattern[0] 或者 pattern[1] 。注意，这个字符可以插入在 text 开头或者结尾的位置。
         * 请你返回插入一个字符后，text 中最多包含多少个等于 pattern 的 子序列 。
         * 子序列 指的是将一个字符串删除若干个字符后（也可以不删除），剩余字符保持原本顺序得到的字符串。
         */
//        public long maximumSubsequenceCount(String text, String pattern) {
//
//
//
//        }
    }

}