/* 2976. Minimum Cost to Convert String I

You are given two 0-indexed strings source and target, both of length n and consisting of lowercase English letters. You are also given two 0-indexed 
character arrays original and changed, and an integer array cost, where cost[i] represents the cost of changing the character original[i] to the character changed[i].

You start with the string source. In one operation, you can pick a character x from the string and change it to the character y at a cost of z if 
there exists any index j such that cost[j] == z, original[j] == x, and changed[j] == y.

Return the minimum cost to convert the string source to the string target using any number of operations. 
If it is impossible to convert source to target, return -1.

Note that there may exist indices i, j such that original[j] == original[i] and changed[j] == changed[i].

Example 1:
Input: source = "abcd", target = "acbe", original = ["a","b","c","c","e","d"], changed = ["b","c","b","e","b","e"], cost = [2,5,5,1,2,20]
Output: 28
Explanation: To convert the string "abcd" to string "acbe":
- Change value at index 1 from 'b' to 'c' at a cost of 5.
- Change value at index 2 from 'c' to 'e' at a cost of 1.
- Change value at index 2 from 'e' to 'b' at a cost of 2.
- Change value at index 3 from 'd' to 'e' at a cost of 20.
The total cost incurred is 5 + 1 + 2 + 20 = 28.
It can be shown that this is the minimum possible cost.

Example 2:
Input: source = "aaaa", target = "bbbb", original = ["a","c"], changed = ["c","b"], cost = [1,2]
Output: 12
Explanation: To change the character 'a' to 'b' change the character 'a' to 'c' at a cost of 1, followed by changing the character 'c' to 'b' at a cost of 2, for a total cost of 1 + 2 = 3. To change all occurrences of 'a' to 'b', a total cost of 3 * 4 = 12 is incurred.

Example 3:
Input: source = "abcd", target = "abce", original = ["a"], changed = ["e"], cost = [10000]
Output: -1
Explanation: It is impossible to convert source to target because the value at index 3 cannot be changed from 'd' to 'e'.
 

Constraints:
1 <= source.length == target.length <= 10^5
source, target consist of lowercase English letters.
1 <= cost.length == original.length == changed.length <= 2000
original[i], changed[i] are lowercase English letters.
1 <= cost[i] <= 10^6
original[i] != changed[i]
*/

class Solution {
    // 可以看成多源路径中的最短权值路径
    // cost数组来代表点与点（及一个字符到另一个字符）之间的权值。
    // 用 Floyd 算法求图中任意两点最短路，得到 dis 矩阵
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        //初始化一个26*26的二维arr, 来储存每个字母转化为其他字母需要消耗的cost
        long[][] arr = new long[26][26];
        for(int i = 0; i < 26; i++){
            Arrays.fill(arr[i], Integer.MAX_VALUE); //首先给每个格子的转化赋上最大值
            arr[i][i] = 0; //相同字母之间的互相转换，不耗费coset
        }

        //把能直接改变的字母和对应的消耗大小 存入arr
        for(int i = 0; i < original.length; i++){
            int s = original[i] - 'a'; //把原有字母改成数值
            int e = changed[i] - 'a'; //把改变后的字母改成数值
            arr[s][e] = Math.min(arr[s][e], cost[i]); //存入对应的消耗值
        }

        // 用 Floyd 算法求图中任意两点最短路径
        // ==> 计算能间接改变得到字母，同时更新最小的消耗 ==> 得到 dis 矩阵
        for(int i = 0; i < 26; i++){ //中间点
            for(int j = 0; j < 26; j++){ //起点
                for(int k = 0; k < 26; k++){ //终点
                    arr[j][k] = Math.min(arr[j][k], arr[j][i] + arr[i][k]);
                }
            }
        }

        long res = 0; //完成改变需要的最小消耗值

        //然后遍历source 和 target 字符串，检查是否能通过改变 完成变化
        for(int i = 0; i < source.length(); i++){
            int pre = source.charAt(i) - 'a';
            int aft = target.charAt(i) - 'a';
            //1. 字母一样，不用改变
            if(pre == aft){
                continue;
            }

            //2.字母不一样, 但没有路径来完成改变
            if(arr[pre][aft] == Integer.MAX_VALUE){
                return -1L;
            } else{ //有路径可以完成改变
                res += arr[pre][aft]; //因为我们在之前的储存中，就是储存的最小消耗值，所以不用再次更新min
            }
        }

        return res;
    }
}
