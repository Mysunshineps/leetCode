package com.psq.learn.week;

/**
 * @Description 周赛-中等程度
 * @Author psq
 * @Date 2022/3/20 16:59
 */
public class MediumCode {

    /**
     * 统计道路上的碰撞次数
     * 题目：在一条无限长的公路上有 n 辆汽车正在行驶。汽车按从左到右的顺序按从 0 到 n - 1 编号，每辆车都在一个 独特的 位置。
     * 给你一个下标从 0 开始的字符串 directions ，长度为 n 。directions[i] 可以是 'L'、'R' 或 'S' 分别表示第 i 辆车是向 左 、向 右 或者 停留 在当前位置。每辆车移动时 速度相同 。
     * 碰撞次数可以按下述方式计算：
     *      当两辆移动方向 相反 的车相撞时，碰撞次数加 2 。
     *      当一辆移动的车和一辆静止的车相撞时，碰撞次数加 1 。
     *      碰撞发生后，涉及的车辆将无法继续移动并停留在碰撞位置。除此之外，汽车不能改变它们的状态或移动方向。
     * 返回在这条道路上发生的 碰撞总次数
     */
    class week285{
        public int countCollisions(String directions) {
            char[] chars = directions.toCharArray();
            int result=0;
            //统计 L 操作出现的碰撞次数
            boolean left = false;
            for(int i=0; i<chars.length; i++){
                //左侧有车辆 S 或 R 时，说明左侧有界(L操作肯定会碰撞)
                if(!left && chars[i] != 'L'){
                    left = true;
                }
                if(left && chars[i] == 'L'){
                    result++;
                }
            }

            //统计 R 操作出现的碰撞次数
            boolean right = false;
            for(int i=chars.length-1; i>=0; i--){
                //右侧有车辆 S 或 L 时，说明右侧有界(R操作肯定会碰撞)
                if(!right && chars[i] != 'R'){
                    right = true;
                }
                if(right && chars[i] == 'R'){
                    result++;
                }
            }
            return result;
        }
    }

    class week292{
        /**
         * 2265. 统计值等于子树平均值的节点数
         *
         * 给你一棵二叉树的根节点 root ，找出并返回满足要求的节点数，要求节点的值等于本身及其子树中值的 平均值 。
         * 注意：
         * n 个元素的平均值可以由 n 个元素 求和 然后再除以 n ，并 向下舍入 到最近的整数。
         * root 的 子树 由 root 和它的所有后代组成。
         *
         */
        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            TreeNode() {}
            TreeNode(int val) { this.val = val; }
            TreeNode(int val, TreeNode left, TreeNode right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }
        }

        int result = 0;
        public int averageOfSubtree(TreeNode root) {
            dfs(root);
            return result;
        }

        //返回数组：[节点和,节点数]
        private int[] dfs(TreeNode root) {
            if (null == root){
                return new int[]{0,0};
            }
            int[] left = dfs(root.left);
            int[] right = dfs(root.right);
            //各个节点和
            int sum = left[0] + right[0] + root.val;
            //节点数
            int nodeNum = left[1] + right[1] + 1;
            int avg = sum/nodeNum;
            if (root.val == avg){
                result++;
            }
            return new int[]{sum, nodeNum};
        }

        /**
         * 统计打字方案数
         * @param pressedKeys
         * @return
         */
        public int countTexts(String pressedKeys) {
            char[] chars = pressedKeys.toCharArray();
            int length = chars.length;
            int mod = (int)(1e9+7);
            /**
             * three[]代表3个字母的键位；four[]代表4个字母的键位(7或9)；
             * three[n]、four[n]分别代表连续n个字母的排列数目
             */
            long[] three = new long[length+1];
            long[] four = new long[length+1];
            three[0] = 1L;
            four[0] = 1L;
            three[1] = 1L;
            four[1] = 1L;

            /**
             * 判断length，防止数组越界
             */
            if (length >= 2){
                three[2] = 2L;
                four[2] = 2L;
            }
            if (length >= 3){
                three[3] = 4L;
                four[3] = 4L;
            }

            for(int i=4; i<=length; i++){
                three[i] = three[i-1] + three[i-2] + three[i-3];
                three[i]%=mod;
                four[i] = four[i-1] + four[i-2] + four[i-3] + four[i-4];
                four[i]%=mod;
            }

            int i = 0;
            long result = 1;
            while (i<length){
                int j=i;
                while (j+1<length && chars[j] == chars[j+1]){
                    j++;
                }
                //其中j-i+1是取连续字符的长度
                boolean isFour = chars[j]=='7' || chars[j] == '9';
                long temp = isFour ? four[j-i+1] : three[j-i+1];
                result = (result*temp)%mod;
                i = j+1;
            }
            return (int)result;
        }
    }

}