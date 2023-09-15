/* Samsara OA Question 4

lights = {{1, 7}, {5, 11}, {7, 9}}; ==> 表示每个灯泡覆盖的区域
index = {7, 1, 5, 10, 9, 15}; ==> 需要检查和统计灯泡数量的坐标

输出: [3, 1, 2, 1, 2, 0] => 每个坐标能被多少个灯泡覆盖到的数量


1 < lights[i][0] < lights[i][1] < 10^5
*/

import java.util.*;
 
class Question4 {
    public static int[] solution(int[][] lights, int[] index) {
        int m = lights.length;
        int n = index.length;
        // 1 < lights[i][0] < lights[i][1] < 10^5
        int[] diff = new int[100000]; // //从1开始 10^5结束

      //首先差分数组，把每个区域的开始和结束放入差分数组中
      //这样在后面改回正常时，我们可以统计每个index位置被多少个灯泡照到
        for(int[] light : lights){
            int start = light[0];
            int end = light[1];
            
            diff[start] += 1; //表示有灯泡覆盖到
            if(end + 1< 100000){ //从1开始 10^5结束
                diff[end + 1] -= 1; //灯泡照不到了
            }
        }

      //差分数组的定义：对于差分分钟求前缀和，则可以复原差分数组为正常数组
      //这样我们就在每个index坐标 统计了灯泡的数量
        int[] count = new int[100000];
        count[0] = diff[0];
        for(int j = 1; j < count.length; j++){
            count[j] = count[j - 1] + diff[j];
        }

      //把 index[] 当中需要查找的值，提取它们的灯泡数量 然后加入到最后结果
        int[] res = new int[n];
        for(int k = 0; k < index.length; k++){
            res[k] = count[index[k]];
        }
        
        return res;
    }

    public static void main(String[] args) {
        int[][] lights = {{1, 7}, {5, 11}, {7, 9}};
        int[] index = {7, 1, 5, 10, 9, 15};
        int[] result = solution(lights, index);
        System.out.println(Arrays.toString(result)); 
    }
}
