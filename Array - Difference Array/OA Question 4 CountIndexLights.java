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
        int[] diff = new int[100000];
        
        for(int[] light : lights){
            int start = light[0];
            int end = light[1];
            
            diff[start] += 1;
            if(end + 1< 101){
                diff[end + 1] -= 1;
            }
        }
        
        HashMap<Integer, Integer> map = new HashMap<>();
        
        int[] count = new int[100000];
        count[0] = diff[0];
        for(int j = 1; j < count.length; j++){
            count[j] = count[j - 1] + diff[j];
            map.put(j, count[j]);
        }
        
        int[] res = new int[n];
        for(int k = 0; k < index.length; k++){
            if(map.containsKey(index[k])){
                res[k] = map.get(index[k]);
            }
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

