/* Lintcode 125 · Backpack II

There are n items and a backpack with size m. Given array A representing the size of each item and array V representing the value of each item.

What's the maximum value can you put into the backpack?

有 n 个物品和一个大小为 m 的背包. 给定数组 A 表示每个物品的大小和数组 V 表示每个物品的价值.

问最多能装入背包的总价值是多大?

Example 1:

Input:

m = 10
A = [2, 3, 5, 7]
V = [1, 5, 2, 4]
Output:

9
Explanation:

Put A[1] and A[3] into backpack, getting the maximum value V[1] + V[3] = 9

Example 2:

Input:

m = 10
A = [2, 3, 8]
V = [2, 5, 8]
Output:

10
Explanation:

Put A[0] and A[2] into backpack, getting the maximum value V[0] + V[2] = 10
*/

// Time: O(m*n)  n: 物品的数量，m: 背包的容量，因为是填格子 选or不选的思路，所以每个格子都会被遍历到，时间复杂度为 O(m*n)
// Space: O(m*n)  开了一个空间为 m * n 的DP数组
// 我的思路：https://app.gitbook.com/o/qh80QyWUCcnWCPQ1oVRB/s/VbuQDeaTHcU4r9BcNf4w/dynamic-programming/0-1-bei-bao-xuan-or-bu-xuan
public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param a: Given n items with size A[i]
     * @param v: Given n items with value V[i]
     * @return: The maximum value
     */

     //A是物品大小，V是物品的价值， m是背包的容量
    public int backPackII(int m, int[] a, int[] v) {
        // write your code here
        /*
        1. 确定dp数组（dp table）以及下标的含义：
            表示从下标 前 i 个物品里任意取，放进 容量为 j 的背包，价值总和 最大是多少。
        2. 确定递推公式：
            a.i = 0 | j = 0 ==> 表示能选择的物品为 0 or 背包容量为 0 ==> 所以价值都为0 
            b. 如果当前物品的容量大于背包剩余空间，不选择放入： dp[i][j] = dp[i - 1][j]
            c. 如果选择放入该物品：dp[i - 1][j - weight[i]] + value[i]
        3. dp数组初始化： 
        4. 确定遍历顺序： 从左上往右下 ==> 当前格子的最大价值 取决于之前 格子的价值
        */

        int n = a.length; //物品的数量

        // i 表示能选择物品的的范围，j 表示背包的容量 
        int[][] dp = new int[n + 1][m + 1];

        //遍历整个DP数组，选 OR 不选的思路
        for(int i = 0; i < n + 1; i++){
            for(int j = 0; j < m + 1; j++){
                //a.i = 0 | j = 0 ==> 表示能选择的物品为 0 or 背包容量为 0 ==> 所以价值都为0 
                if(i == 0 || j == 0){
                    dp[i][j] = 0;
                } else if (a[i - 1] > j){ //如果当前物品的容量大于背包剩余空间, 无法放入当前物品
                    //==> 因为存在前导0的部分，所以第i个物品对应的大小为A[i - 1] 价值为 V[i - 1]
                    dp[i][j] = dp[i - 1][j]; //因为无法选择，所以当前的价值就是[0, i-1]范围的最大价值
                } else{ //如果当前背包容量使大于当前物品容量的，选择放入和不放都可以，比较max值
                    //==> 因为存在前导0的部分，所以第i个物品对应的大小为A[i - 1] 价值为 V[i - 1]
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - a[i - 1]] + v[i - 1]);
                }
            }
        }

        return dp[a.length][m]; //输出当物品数量为n，背包容量为m时 能得到的最大价值
    }
}
