package com.psq.learn.offer;

/**
 * @Description 中等程度
 * @Author psq
 * @Date 2022/2/15 17:00
 */
public class MediumCode {
    public static void main(String[] args) {
        System.out.println("Hello offer-MediumCode!");
    }

    /**
     * 二维数组中的查找
     * 题目：在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     */
    class day6{
        /**
         * 方法一：线性查找
         * 相比双重循环暴力破解更好
         *
         * 解题思路：
         * 由于给定的二维数组具备每行从左到右递增以及每列从上到下递增的特点，当访问到一个元素时，可以排除数组中的部分元素。
         * 从二维数组的右上角开始查找。如果当前元素等于目标值，则返回 true。如果当前元素大于目标值，则移到左边一列。如果当前元素小于目标值，则移到下边一行。
         * 可以证明这种方法不会错过目标值。如果当前元素大于目标值，说明当前元素的下边的所有元素都一定大于目标值，因此往下查找不可能找到目标值，往左查找可能找到目标值。如果当前元素小于目标值，说明当前元素的左边的所有元素都一定小于目标值，因此往左查找不可能找到目标值，往下查找可能找到目标值。
         * 若数组为空，返回 false
         * 初始化行下标为 0，列下标为二维数组的列数减 1
         * 重复下列步骤，直到行下标或列下标超出边界
         * 获得当前下标位置的元素 num
         * 如果 num 和 target 相等，返回 true
         * 如果 num 大于 target，列下标减 1
         * 如果 num 小于 target，行下标加 1
         * 循环体执行完毕仍未找到元素等于 target ，说明不存在这样的元素，返回 false`
         *
         * 复杂度分析：
         * 时间复杂度：O(n+m)。访问到的下标的行最多增加 n 次，列最多减少 m 次，因此循环体最多执行 n + m 次。
         * 空间复杂度：O(1)。
         */
        public boolean findNumberIn2DArray(int[][] matrix, int target) {
            if(null == matrix || matrix.length == 0 || matrix[0].length == 0){
                return false;
            }
            int rows = matrix.length, column = matrix[0].length - 1;
            int row = 0;
            while(row < rows && column >= 0){
                if(matrix[row][column] == target){
                    return true;
                }else if(matrix[row][column] > target){
                    column--;
                }else{
                    row++;
                }
            }
            return false;
        }
    }
}
