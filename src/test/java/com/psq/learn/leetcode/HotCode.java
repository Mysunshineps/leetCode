package com.psq.learn.leetcode;

import com.psq.learn.MyApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

/**
 * @Description
 * @Author psq
 * @Date 2022/2/15 15:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HotCode {

    /**
     * 两数之和
     * 给定一个整数数组nums和一个整数目标值target，请你在该数组中找出和为目标值target的那两个整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * 你可以按任意顺序返回答案。
     * 链接：https://leetcode-cn.com/problems/two-sum
     */
    class day1 {
        /**
         * 方法一：暴力枚举,时间复杂度O(N*N),空间复杂度O(1)
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

    class day2{

    }



}
