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

    class doubleWeek_74 {
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
            if (null == nums || nums.length % 2 == 1) {
                return false;
            }

            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(nums[i])) {
                    map.put(nums[i], map.get(nums[i]) + 1);
                } else {
                    map.put(nums[i], 1);
                }
            }

            for (Integer num : map.values()) {
                if (num % 2 == 1) {
                    return false;
                }
            }
            return true;
        }
    }

    class week292{
        /**
         * 题目：2264. 字符串中最大的 3 位相同数字
         *
         * 给你一个字符串 num ，表示一个大整数。如果一个整数满足下述所有条件，则认为该整数是一个 优质整数 ：
         * 该整数是 num 的一个长度为 3 的 子字符串 。
         * 该整数由唯一一个数字重复 3 次组成。
         * 以字符串形式返回 最大的优质整数 。如果不存在满足要求的整数，则返回一个空字符串 "" 。
         *
         * 注意：
         * 子字符串 是字符串中的一个连续字符序列。
         * num 或优质整数中可能存在 前导零
         */
        public String largestGoodInteger(String num) {
            int max = -1;
            char[] chars = num.toCharArray();
            for (int i=0; i<chars.length-2; i++){
                if ((chars[i]-'0') == (chars[i+1]-'0') && (chars[i+1]-'0') == (chars[i+2]-'0')){
                    max = Math.max(max, (chars[i]-'0'));
                }
            }
            if (max < 0){
                return "";
            }
            return ""+max+max+max;
        }
    }

}