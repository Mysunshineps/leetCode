package com.psq.learn.offer;

import java.util.HashMap;

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

    /**
     * 重建二叉树
     * 题目：输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点；假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     */
    class day8{
        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            TreeNode(int x) { val = x; }
        }

        /**
         * 方法：递归
         * 解题思路：
         * 对于任意一颗树而言，前序遍历的形式总是
         * [ 根节点, [左子树的前序遍历结果], [右子树的前序遍历结果] ]
         * 即根节点总是前序遍历中的第一个节点。
         * 而中序遍历的形式总是
         * [ [左子树的中序遍历结果], 根节点, [右子树的中序遍历结果] ]
         * 只要我们在中序遍历中定位到根节点，那么我们就可以分别知道左子树和右子树中的节点数目。由于同一颗子树的前序遍历和中序遍历的长度显然是相同的，因此我们就可以对应到前序遍历的结果中，对上述形式中的所有左右括号进行定位。
         * 这样以来，我们就知道了左子树的前序遍历和中序遍历结果，以及右子树的前序遍历和中序遍历结果，我们就可以递归地对构造出左子树和右子树，再将这两颗子树接到根节点的左右位置。
         *
         * 细节
         * 在中序遍历中对根节点进行定位时，一种简单的方法是直接扫描整个中序遍历的结果并找出根节点，但这样做的时间复杂度较高。我们可以考虑使用哈希表来帮助我们快速地定位根节点。对于哈希映射中的每个键值对，键表示一个元素（节点的值），值表示其在中序遍历中的出现位置。在构造二叉树的过程之前，我们可以对中序遍历的列表进行一遍扫描，就可以构造出这个哈希映射。在此后构造二叉树的过程中，我们就只需要 O(1) 的时间对根节点进行定位了。
         *
         * 复杂度分析：
         * 时间复杂度：O(n)，其中 n 是树中的节点个数。
         * 空间复杂度：O(n)，除去返回的答案需要的 O(n) 空间之外，我们还需要使用 O(n) 的空间存储哈希映射，以及 O(h)（其中 h 是树的高度）的空间表示递归时栈空间。这里 h < n，所以总空间复杂度为 O(n)。
         *
         */
        class Solution1 {
            //存储二叉树中序排列的数据(以值为key,下标为value)以此来获取中序排列中根节点的位置
            private HashMap<Integer, Integer> inOrderMap = new HashMap<>();

            public TreeNode buildTree(int[] preorder, int[] inorder) {
                int length = preorder.length;
                if(length == 1){
                    return new TreeNode(preorder[0]);
                }
                if(length != inorder.length){
                    return null;
                }
                for(int i=0; i<length; i++){
                    inOrderMap.put(inorder[i], i);
                }
                return buildMyTree(preorder, inorder, 0, length-1, 0, length-1);
            }

            //合并二叉树
            public TreeNode buildMyTree(int[] preorder, int[] inorder, Integer preStart, Integer preEnd, Integer inStart, Integer inEnd){
                if(preStart > preEnd){
                    return null;
                }
                //二叉树先序排列中，第一个就是根节点
                //获取根据点的值
                int root = preorder[preStart];
                //构建二叉树根节点
                TreeNode result = new TreeNode(root);
                //获取中序排序中根节点的下标
                Integer inMiddleNum = inOrderMap.get(root);
                //获取左子树的节点的数目
                Integer leftNum = inMiddleNum - inStart;

                //递归构建左子树，先序中左子树在(先序位置开始之后)到(先序位置开始+左子树长度)的位置
                //中序中左子树在(中序开始)到(根节点位置-1)的位置
                result.left = buildMyTree(preorder, inorder, preStart+1, preStart+leftNum, inStart, inMiddleNum-1);

                //递归构建右子树，先序中右子树在(先序位置开始之后+左子树长度)之后的位置
                //中序中右子树在(根节点位置)之后的位置
                result.right = buildMyTree(preorder, inorder, preStart+leftNum+1, preEnd, inMiddleNum+1, inEnd);

                return result;
            }
        }

    }
}
