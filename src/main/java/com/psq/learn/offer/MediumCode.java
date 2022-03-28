package com.psq.learn.offer;

import java.util.*;

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

    /**
     *  矩阵中的路径
     *  题目：给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用
     */
    class day12{
        class Solution {
            /**
             *TODO：方法：深度优先搜索(DFS) + 剪枝
             * 解题思路：
             * 深度优先搜索： 可以理解为暴力法遍历矩阵中所有字符串可能性。DFS 通过递归，先朝一个方向搜到底，再回溯至上个节点，沿另一个方向搜索，以此类推。
             * 剪枝： 在搜索中，遇到 这条路不可能和目标字符串匹配成功 的情况（例如：此矩阵元素和目标字符不同、此元素已被访问），则应立即返回，称之为 可行性剪枝 。
             *  DFS 解析：
             * 递归参数： 当前元素在矩阵 board 中的行列索引 i 和 j ，当前目标字符在 word 中的索引 k 。
             * 终止条件：
             * 返回 false ： (1) 行或列索引越界 或 (2) 当前矩阵元素与目标字符不同 或 (3) 当前矩阵元素已访问过 （(3)可合并至 (2)，将访问的值重新赋值） 。
             * 返回 true ： k = len(word) - 1 ，即字符串 word 已全部匹配。
             * 递推工作：
             * 标记当前矩阵元素： 将 board[i][j] 修改为 空字符 '' ，代表此元素已访问过，防止之后搜索时重复访问。
             * 搜索下一单元格： 朝当前元素的 上、下、左、右 四个方向开启下层递归，使用 或 连接 （代表只需找到一条可行路径就直接返回，不再做后续 DFS ），并记录结果至 res 。
             * 还原当前矩阵元素： 将 board[i][j] 元素还原至初始值，即 word[k] 。
             * 返回值： 返回布尔量 res ，代表是否搜索到目标字符串
             *
             * 复杂度分析：
             * M, N分别为矩阵行列大小， K 为字符串 word 长度。
             * 时间复杂度 O(3^KMN) ： 最差情况下，需要遍历矩阵中长度为 K 字符串的所有方案，时间复杂度为 O(3^K)；矩阵中共有 MN 个起点，时间复杂度为 O(MN) 。
             *  方案数计算： 设字符串长度为 K ，搜索中每个字符有上、下、左、右四个方向可以选择，舍弃回头（上个字符）的方向，剩下 3 种选择，因此方案数的复杂度为 O(3^K)
             *
             * 空间复杂度 O(K) ： 搜索过程中的递归深度不超过 K ，因此系统因函数调用累计使用的栈空间占用 O(K)（因为函数返回后，系统调用的栈空间会释放）。最坏情况下 K = MNK ，递归深度为 MNMN ，此时系统栈使用 O(MN)的额外空间。
             */
            public boolean exist(char[][] board, String word) {
                char[] words = word.toCharArray();
                for(int i=0; i<  board.length; i++){
                    for(int j=0; j<board[0].length; j++){
                        if(dfs(board, words, i, j, 0)){
                            return true;
                        }
                    }
                }
                return false;
            }

            //广度优先搜索
            public boolean dfs(char[][] board, char[] words, int i, int j, int k){
                //越界及当前单词不匹配都返回fasle
                if(i<0 || i >= board.length || j<0 || j >= board[0].length || board[i][j] != words[k]){
                    return false;
                }
                //代表words字符已全部匹配
                if(k == (words.length - 1)){
                    return true;
                }
                //先赋值，代表已被搜索过,下面进行判断即可区分跳过
                board[i][j] = '\0';

                //分别对上下左右进行判断
                boolean result = (dfs(board, words, i+1, j, k+1) || dfs(board, words, i-1, j, k+1)
                        ||dfs(board, words, i, j+1, k+1) || dfs(board, words, i, j-1 ,k+1));
                //再还原矩阵的值
                board[i][j] = words[k];
                return result;
            }
        }
    }

    /**
     * TODO：周赛-6010
     * 完成旅途的最少时间 显示英文描述
     * 给你一个数组 time ，其中 time[i] 表示第 i 辆公交车完成 一趟旅途 所需要花费的时间。
     * 每辆公交车可以 连续 完成多趟旅途，也就是说，一辆公交车当前旅途完成后，可以 立马开始 下一趟旅途。每辆公交车 独立 运行，也就是说可以同时有多辆公交车在运行且互不影响。
     * 给你一个整数 totalTrips ，表示所有公交车 总共 需要完成的旅途数目。请你返回完成 至少 totalTrips 趟旅途需要花费的 最少 时间。
     */
    class Week_6010 {
        /**
         * 方法：二分查找
         *
         */
        public long minimumTime(int[] time, int totalTrips) {
            long r=0;
            long max = 0;
            for(int a: time){
                max = Math.max(max,a);
            }
            r= max*totalTrips;
            long l = 1;
            while(l<r){
                long mid = (l+r)/2;
                if(check(time, mid, totalTrips)){
                    r = mid;
                }else{
                    l = mid +1;
                }
            }
            return l;
        }
        public boolean check(int[] time, long mid, int totalTrips){
            long count = 0;
            for(int a : time){
                count += mid/a;
            }
            return count>=totalTrips;
        }
    }

    /**
     * 机器人的运动范围
     * 题目：地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
     */
    class day13 {
        /**
         *方法一：广度优先搜索
         * 解题思路:
         * 我们将行坐标和列坐标数位之和大于 k 的格子看作障碍物，那么这道题就是一道很传统的搜索题目，我们可以使用广度优先搜索或者深度优先搜索来解决它，本文选择使用广度优先搜索的方法来讲解。
         * 那么如何计算一个数的数位之和呢？我们只需要对数 x 每次对 10 取余，就能知道数 x 的个位数是多少，然后再将 x 除 10，这个操作等价于将 x 的十进制数向右移一位，删除个位数（类似于二进制中的 >> 右移运算符），不断重复直到 x 为 0 时结束。
         * 同时这道题还有一个隐藏的优化：我们在搜索的过程中搜索方向可以缩减为向右和向下，而不必再向上和向左进行搜索。如下图，我们展示了 16 * 16 的地图随着限制条件 k 的放大，可行方格的变化趋势，每个格子里的值为行坐标和列坐标的数位之和，蓝色方格代表非障碍方格，即其值小于等于当前的限制条件 k。我们可以发现随着限制条件 k 的增大，(0, 0) 所在的蓝色方格区域内新加入的非障碍方格都可以由上方或左方的格子移动一步得到。而其他不连通的蓝色方格区域会随着 k 的增大而连通，且连通的时候也是由上方或左方的格子移动一步得到，因此我们可以将我们的搜索方向缩减为向右或向下
         *
         * 复杂度分析：
         * 时间复杂度：O(mn)，其中 m 为方格的行数，n 为方格的列数。考虑所有格子都能进入，那么搜索的时候一个格子最多会被访问的次数为常数，所以时间复杂度为 O(mn)。
         * 空间复杂度：O(mn)，其中 m 为方格的行数，n 为方格的列数。搜索的时候需要一个大小为 O(mn)O 的标记结构用来标记每个格子是否被走过。
         */
        public int movingCount(int m, int n, int k) {
            if(k == 0){
                return 1;
            }
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{0,0});
            //向右方向的数组
            int[] dx = {0,1};
            //向下方向的数组
            int[] dy = {1,0};
            //标识是否被访问过
            boolean[][] vis = new boolean[m][n];
            vis[0][0] = true;
            //计数，默认{0，0}已被访问
            int result = 1;
            while(!queue.isEmpty()){
                int[] cell = queue.poll();
                int x = cell[0];
                int y = cell[1];
                for(int i=0; i<2; i++){
                    //当i=0时向下移位；当i=1时向右移位
                    int tx = dx[i] + x;
                    int ty = dy[i] + y;
                    if(tx < 0 || tx >= m || ty <0 || ty>=n || vis[tx][ty] || (get(tx) + get(ty)) > k){
                        continue;
                    }
                    queue.offer(new int[]{tx, ty});
                    vis[tx][ty] = true;
                    result++;
                }
            }
            return result;
        }

        //获取数字的数位和
        public int get(int x){
            int res = 0;
            while(x != 0){
                //取模，获取最后一个数字
                res += x % 10;
                //取整或者说是进位，直到不为0时
                x /= 10;
            }
            return res;
        }
    }

    /**
     *  剪绳子
     *  题目：给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
     *  提示：2 <= n <= 58
     */
    class day14{
        /**
         * TODO：数学推导-取最优长度
         * 解题思路：
         *  设将绳子按照 x 长度等分为 a 段，即 n=ax ，则乘积为 x^a。观察以下公式，由于 n 为常数，因此当 取最大值时，乘积取得最大值；
         *  根据分析，可将问题转化为求 y = x^1/x极大值，因此对 x 求导数；易得驻点为 x_0 = e≈2.7；因为x必须为整数，所以取2和3代入x^1/x做比较；可得取3更大，所以最优解为3
         *
         *  所以切分规则
         *  最优： 3 。把绳子尽可能切为多个长度为 3 的片段，留下的最后一段绳子的长度可能为 0,1,2三种情况。
         * 次优： 2 。若最后一段绳子长度为 2 ；则保留，不再拆为 1+1 。
         * 最差： 1 。若最后一段绳子长度为 1 ；则应把一份 3 + 1替换为 2 + 2，因为 2 x 2 > 3 x1。
         *
         *  算法流程：
         * 当 n≤3 时，按照规则应不切分，但由于题目要求必须剪成 m>1段，因此必须剪出一段长度为 1 的绳子，即返回 n - 1 。
         * 当 n>3 时，求 n 除以 3 的 整数部分 a 和 余数部分 b （即 n = 3a + b），并分为以下三种情况：
         * 当 b = 0时，直接返回 3^a
         * 当 b = 1时，要将一个 1 + 3转换为 2+2，因此返回 2x2x3^{a-1}
         * 当 b = 2时，返回 2x3^a
         *
         * 复杂度分析：
         * 时间复杂度 O(1) ： 仅有求整、求余、次方运算。
         *  求整和求余运算：资料提到不超过机器数的整数可以看作是 O(1) ；
         *  幂运算：查阅资料，提到浮点取幂为 O(1)。
         * 空间复杂度 O(1) ： 变量 a 和 b 使用常数大小额外空间。
         */
        public int cuttingRope(int n) {
            if(n <= 3){
                return n-1;
            }
            //取整
            int a = n/3;
            //取余数
            int b = n%3;
            if(b == 2){
                return 2*(int)Math.pow(3, a);
            }
            if(b == 1){
                return 2*2*(int)Math.pow(3, a-1);
            }
            return (int)Math.pow(3, a);
        }
    }

    /**
     * 剪绳子 II
     * 题目：给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1] 。请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     * 提示：2 <= n <= 1000；会存在int越界
     */
    class day15{
        /**
         * 与day14解题思路一致；
         * TODO：但要考虑越界的问题
         * 具体看：https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof/solution/mian-shi-ti-14-ii-jian-sheng-zi-iitan-xin-er-fen-f/
         */
        public int cuttingRope(int n) {
            if(n <= 3) {
                return n - 1;
            }
            int b = n % 3, p = 1000000007;
            long rem = 1, x = 3;
            for(int a = n / 3 - 1; a > 0; a /= 2) {
                if(a % 2 == 1) {
                    rem = (rem * x) % p;
                }
                x = (x * x) % p;
            }
            if(b == 0)  {
                return (int)(rem * 3 % p);
            }
            if(b == 1) {
                return (int)(rem * 4 % p);
            }
            return (int)(rem * 6 % p);
        }
    }

    /**
     * 数值的整数次方
     * 题目：实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题
     */
    class day17{
        /**
         * 解题思路：
         * 求 x^n;最简单的方法是通过循环将 n 个 x 乘起来，依次求 x^1, x^2, ..., x^{n-1}, x^n，时间复杂度为 O(n) 。
         * 快速幂法 可将时间复杂度降低至 O(log_2 n)，以下从 “二分法” 和 “二进制” 两个角度解析快速幂法
         * 具体查看：https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/solution/mian-shi-ti-16-shu-zhi-de-zheng-shu-ci-fang-kuai-s/
         */
        public double myPow(double x, int n) {
            if(x == 0) return 0;
            long b = n;
            double res = 1.0;
            if(b < 0) {
                x = 1 / x;
                b = -b;
            }
            while(b > 0) {
                if((b & 1) == 1) res *= x;
                x *= x;
                b >>= 1;
            }
            return res;
        }
    }

    /**
     * 树的子结构
     * 题目：输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)；B是A的子结构， 即 A中有出现和B相同的结构和节点值
     */
    class day25{
        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            TreeNode(int x) { val = x; }
        }

        /**
         *
         *算法流程：
         * 名词规定：树 A 的根节点记作 节点 A ，树 B 的根节点称为 节点 B 。
         * 1.recur(A, B) 函数-终止条件：
         *  当节点 B 为空：说明树 B 已匹配完成（越过叶子节点），因此返回 true ；
         *  当节点 A 为空：说明已经越过树 A叶子节点，即匹配失败，返回 false ；
         *  当节点 A 和 B 的值不同：说明匹配失败，返回 false ；
         *  返回值：
         *  判断 AA 和 B 的左子节点是否相等，即 recur(A.left, B.left) ；
         *  判断 A 和 B 的右子节点是否相等，即 recur(A.right, B.right) ；
         * 2.isSubTree(A, B) 函数：
         * 特例处理： 当 树 A 为空 或 树 B 为空 时，直接返回 false ；
         * 返回值： 若树 B 是树 A 的子结构，则必满足以下三种情况之一，因此用或 || 连接；
         *  以 节点 A 为根节点的子树 包含树 B ，对应 recur(A, B)；
         *  树 B 是 树 A 左子树 的子结构，对应 isSubStructure(A.left, B)；
         *  树 B 是 树 A 右子树 的子结构，对应 isSubStructure(A.right, B)；
         *
         * 复杂度分析：
         *  时间复杂度 O(MN) ： 其中 M,N分别为树 A 和 树 B 的节点数量；先序遍历树 A 占用 O(M) ，每次调用 recur(A, B) 判断占用 O(N) 。
         *  空间复杂度 O(M) ： 当树 A 和树 B 都退化为链表时，递归调用深度最大。当 M≤N 时，遍历树 A 与递归判断的总递归深度为 M ；当 M>N 时，最差情况为遍历至树 A 叶子节点，此时总递归深度为 M。
         */
        public boolean isSubStructure(TreeNode A, TreeNode B) {
            return  (null!=A && null!=B) && (isSubTree(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));

        }

        private boolean isSubTree(TreeNode ta, TreeNode tb){
            if(tb == null){
                return true;
            }
            if(ta == null || ta.val != tb.val){
                return false;
            }
            return (isSubTree(ta.left, tb.left) && isSubTree(ta.right, tb.right));
        }
    }

    /**
     * 栈的压入、弹出序列
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列
     */
    class day31 {
        /**
         * 辅助栈，有待优化
         */
        public boolean validateStackSequences(int[] pushed, int[] popped) {
            int k = 0;
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < pushed.length; i++) {
                stack.push(pushed[i]);
                while (!stack.empty() && stack.peek().equals(popped[k])) {
                    stack.pop();
                    k++;
                }
            }
            if (stack.empty()) {
                return true;
            }
            return false;
        }
    }

    /**
     * 二叉搜索树的后序遍历序列
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同
     */
    class day33{

        /**
         * 解题思路：
         * 后序遍历定义： [ 左子树 | 右子树 | 根节点 ] ，即遍历顺序为 “左、右、根” 。
         * 二叉搜索树定义： 左子树中所有节点的值 < 根节点的值；右子树中所有节点的值 > 根节点的值；其左、右子树也分别为二叉搜索树
         *  方法一：递归分治
         *      终止条件： 当 i≥j ，说明此子树节点数量 ≤1 ，无需判别正确性，因此直接返回 true ；
         * 递推工作：
         *      划分左右子树： 遍历后序遍历的 [i, j] 区间元素，寻找 第一个大于根节点 的节点，索引记为 m 。此时，可划分出左子树区间 [i,m-1]、右子树区间 [m, j - 1]根节点索引 j 。
         * 判断是否为二叉搜索树：
         *      左子树区间 [i,m−1] 内的所有节点都应 <postorder[j] 。而第 1.划分左右子树 步骤已经保证左子树区间的正确性，因此只需要判断右子树区间即可。
         *      右子树区间 [m,j−1] 内的所有节点都应 >postorder[j] 。实现方式为遍历，当遇到≤postorder[j] 的节点则跳出；则可通过 p = j判断是否为二叉搜索树。
         * 返回值： 所有子树都需正确才可判定正确，因此使用 与逻辑符&& 连接。
         *      p=j ： 判断 此树 是否正确。
         *      recur(i, m - 1) ： 判断 此树的左子树 是否正确。
         *      recur(m, j - 1) ： 判断 此树的右子树 是否正确
         *
         * 复杂度分析：
         * 时间复杂度 O(N^2)： 每次调用 recur(i,j)减去一个根节点，因此递归占用 O(N) ；最差情况下（即当树退化为链表），每轮递归都需遍历树所有节点，占用 O(N) 。
         * 空间复杂度 O(N)： 最差情况下（即当树退化为链表），递归深度将达到 N 。

         */
        public boolean verifyPostorder(int[] postorder) {
            if(postorder.length<=2){
                return true;
            }
            return res(postorder, 0, postorder.length-1);
        }

        private boolean res(int[] postorder, int start, int end){
            if(start>=end){
                return true;
            }
            int p = start;
            while(postorder[end] > postorder[p]){
                p++;
            }
            int temp = p;
            while(postorder[end] < postorder[p] && end >= start){
                p++;
            }
            return end == p && res(postorder, start, temp-1) && res(postorder, temp, end-1);
        }
    }

    /**
     * 二叉树中和为某一值的路径
     * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
     * 叶子节点 是指没有子节点的节点
     */
    class day34{
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

        /**
         *方法一：深度优先搜索
         * 思路及算法
         * 我们可以采用深度优先搜索的方式，枚举每一条从根节点到叶子节点的路径。当我们遍历到叶子节点，且此时路径和恰为目标和时，我们就找到了一条满足条件的路径
         *
         *复杂度分析
         * 时间复杂度：O(N^2)，其中 N是树的节点数。在最坏情况下，树的上半部分为链状，下半部分为完全二叉树，并且从根节点到每一个叶子节点的路径都符合题目要求。此时，路径的数目为 O(N)，并且每一条路径的节点个数也为 O(N)，因此要将这些路径全部添加进答案中，时间复杂度为 O(N^2)
         * 空间复杂度：O(N)，其中 N是树的节点数。空间复杂度主要取决于栈空间的开销，栈中的元素个数不会超过树的节点数
         */
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> temp = new LinkedList<>();
        public List<List<Integer>> pathSum(TreeNode root, int target) {
            findNode(root, target);
            return result;
        }

        private void findNode(TreeNode root, int target){
            if(null == root){
                return;
            }
            temp.offerLast(root.val);
            target -= root.val;
            if(root.left == null && root.right == null && target == 0){
                result.add(new LinkedList<>(temp));
            }
            findNode(root.left, target);
            findNode(root.right, target);
            temp.pollLast();
            return;
        }
    }

    /**
     * 复杂链表的复制
     * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null
     * 注意：别引用了原始节点
     */
    class day35{
        class Node {
            int val;
            Node next;
            Node random;

            public Node(int val) {
                this.val = val;
                this.next = null;
                this.random = null;
            }
        }

        /**
         * 方法一：回溯 + 哈希表
         * 思路及算法
         * 本题要求我们对一个特殊的链表进行深拷贝。如果是普通链表，我们可以直接按照遍历的顺序创建链表节点。而本题中因为随机指针的存在，当我们拷贝节点时，「当前节点的随机指针指向的节点」可能还没创建，因此我们需要变换思路。一个可行方案是，我们利用回溯的方式，让每个节点的拷贝操作相互独立。对于当前节点，我们首先要进行拷贝，然后我们进行「当前节点的后继节点」和「当前节点的随机指针指向的节点」拷贝，拷贝完成后将创建的新节点的指针返回，即可完成当前节点的两指针的赋值。
         * 具体地，我们用哈希表记录每一个节点对应新节点的创建情况。遍历该链表的过程中，我们检查「当前节点的后继节点」和「当前节点的随机指针指向的节点」的创建情况。如果这两个节点中的任何一个节点的新节点没有被创建，我们都立刻递归地进行创建。当我们拷贝完成，回溯到当前层时，我们即可完成当前节点的指针赋值。注意一个节点可能被多个其他节点指向，因此我们可能递归地多次尝试拷贝某个节点，为了防止重复拷贝，我们需要首先检查当前节点是否被拷贝过，如果已经拷贝过，我们可以直接从哈希表中取出拷贝后的节点的指针并返回即可。
         * 注意在实际代码中，我们需要特别判断给定节点为空节点的情况
         *
         * 复杂度分析
         * 时间复杂度：O(n)，其中 n 是链表的长度。对于每个节点，我们至多访问其「后继节点」和「随机指针指向的节点」各一次，均摊每个点至多被访问两次。
         * 空间复杂度：O(n)，其中 n 是链表的长度。为哈希表的空间开销
         */
        HashMap<Node,Node> map = new HashMap<>();
        public Node copyRandomList(Node head) {
            if(null == head){
                return null;
            }
            if(!map.containsKey(head)){
                Node node = new Node(head.val);
                map.put(head, node);
                node.next = copyRandomList(head.next);
                node.random = copyRandomList(head.random);
            }
            return map.get(head);
        }
    }

    /**
     * 美化数组的最少删除数
     * 题目：
     * 给你一个下标从 0 开始的整数数组 nums ，如果满足下述条件，则认为数组 nums 是一个 美丽数组 ：
     * a)nums.length 为偶数
     * b)对所有满足 i % 2 == 0 的下标 i ，nums[i] != nums[i + 1] 均成立
     * 注意，空数组同样认为是美丽数组。
     * 你可以从 nums 中删除任意数量的元素。当你删除一个元素时，被删除元素右侧的所有元素将会向左移动一个单位以填补空缺，而左侧的元素将会保持 不变 。
     * 返回使 nums 变为美丽数组所需删除的 最少 元素数目。
     */
    class day40{
        class Solution {
            /**
             * 解题思路：
             * 由于删除一个元素后，删除元素的右侧的所有元素将向左移动。
             * 栈可以实现，而其他数据结构实现这个的时间复杂度太高，不推荐使用
             */
            public int minDeletion(int[] nums) {
                int n = nums.length;
                if (n == 0) return 0;
                Deque<Integer> deque = new LinkedList<>();
                deque.push(nums[0]);
                for (int i = 1; i < n; i++) {
            /*
                对所有满足 i % 2 == 0 的下标 i ，nums[i] != nums[i + 1] 均成立
             */
                    //当栈的大小为偶数时，说明此时可以将数据直接添加到栈中
                    if (deque.size() % 2 != 0){
                        int val = deque.peek();
                        //当为奇数时，要判断是否与栈顶的元素相同，如果相同，则不能添加
                        if (nums[i] == val) {
                            continue;
                        }
                    }
                    deque.push(nums[i]);

                }
                if (deque.size() % 2 == 0) return n - deque.size();
                //当栈的大小为奇数时，需要减去栈顶元素，所以删除的次数要加1
                return n - deque.size() + 1;
            }
        }
    }
}
