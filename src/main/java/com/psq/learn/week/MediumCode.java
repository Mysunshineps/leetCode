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

}