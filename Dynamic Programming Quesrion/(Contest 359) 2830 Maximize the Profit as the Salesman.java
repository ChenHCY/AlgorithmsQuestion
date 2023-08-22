/*Leetcode (Contest 359) 2830 Maximize the Profit as the Salesman

You are given an integer n representing the number of houses on a number line, numbered from 0 to n - 1.

Additionally, you are given a 2D integer array offers where offers[i] = [starti, endi, goldi], 
indicating that ith buyer wants to buy all the houses from starti to endi for goldi amount of gold.

As a salesman, your goal is to maximize your earnings by strategically selecting and selling houses to buyers.

Return the maximum amount of gold you can earn.

Note that different buyers can't buy the same house, and some houses may remain unsold.

Example 1:

Input: n = 5, offers = [[0,0,1],[0,2,2],[1,3,2]]
Output: 3
Explanation: There are 5 houses numbered from 0 to 4 and there are 3 purchase offers.
We sell houses in the range [0,0] to 1st buyer for 1 gold and houses in the range [1,3] to 3rd buyer for 2 golds.
It can be proven that 3 is the maximum amount of gold we can achieve.

Example 2:

Input: n = 5, offers = [[0,0,1],[0,2,10],[1,3,2]]
Output: 10
Explanation: There are 5 houses numbered from 0 to 4 and there are 3 purchase offers.
We sell houses in the range [0,2] to 2nd buyer for 10 golds.
It can be proven that 10 is the maximum amount of gold we can achieve.
 

Constraints:

1 <= n <= 10^5
1 <= offers.length <= 10^5
offers[i].length == 3
0 <= starti <= endi <= n - 1
1 <= goldi <= 10^3
*/

class Solution {
    // n: 房屋的编号 Offers： [卖房屋的开始的编号, 结束的编号, 能获得的收入]
    // DP[i + 1]： 表示销售 编号不超过 i 的房屋 的最大盈利。
    public int maximizeTheProfit(int n, List<List<Integer>> offers) {
        //邻接表，储存每个offer中售卖的房屋 和 可以取的利润
        List<int[]>[] houses = new ArrayList[n];
        
        //给邻接表的每个位置都首先创建空的list队列
        Arrays.setAll(houses, e -> new ArrayList<>());

        //然后在每个售卖房屋的结束编号，加入【开始的编号，赚取的利润】
        for(List<Integer> offer : offers){
            houses[offer.get(1)].add(new int[]{offer.get(0), offer.get(2)});
        }

        //DP 记忆化搜索： DP[i]： 表示销售 编号不超过 i 的房屋 的最大盈利。=》因为下标不能为负，所以是i+1
        // ==> 选或不选:  选择卖不卖当前编号的房子：如果不卖：利润：DP[i] = DP[i - 1]; 
        //                                       如果卖：利润：DP[i] = DP[start - 1] + 当前offer的利润
        // ==> 枚举选哪个： 当前编号的房子 可能存在多个售卖offer，我们要一个一个枚举，然后选择利润最大的offer
        int[] dp = new int[n + 1]; //=》因为下标不能为负，所以是i+1，DP数组的大小为n+1

        for(int end = 0; end < n; end++){
            //选择卖不卖当前编号的房子：如果不卖：利润：DP[i] = DP[i - 1]; 
            //因为java的边界问题，index不能为负数，所以在DP中储存值的index要集体+1
            dp[end + 1] = dp[end];

            //如果卖：利润：DP[i] = DP[start - 1] + 当前offer的利润
            //但当前编号的房子 可能存在多个售卖offer，所以我们要一个一个枚举，然后选择利润最大的offer
            for(int[] sellHouses : houses[end]){
                dp[end + 1] = Math.max(dp[end + 1], dp[sellHouses[0]] + sellHouses[1]);
            }
        }

        return dp[n];
    }
}
