/* Leetcode 313. Super Ugly Number

A super ugly number is a positive integer whose prime factors are in the array primes.

Given an integer n and an array of integers primes, return the nth super ugly number.

The nth super ugly number is guaranteed to fit in a 32-bit signed integer.


Example 1:

Input: n = 12, primes = [2,7,13,19]
Output: 32
Explanation: [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first 12 super ugly numbers given primes = [2,7,13,19].

Example 2:

Input: n = 1, primes = [2,3,5]
Output: 1
Explanation: 1 has no prime factors, therefore all of its prime factors are in the array primes = [2,3,5].
 

Constraints:

1 <= n <= 105
1 <= primes.length <= 100
2 <= primes[i] <= 1000
primes[i] is guaranteed to be a prime number.
All the values of primes are unique and sorted in ascending order.
*/

/* 此题是给一个n和一个primes[] array，找到第nth个ugly number

思路：  Ugly number的特性，每次最小的数 * primes[] array number里面的数 得到的数一定是ugly number

==》所以 第 n 个超级丑数 是基于 前面某一个超级丑数乘以某一个 primes[i] 得到的

==》存在递推关系，可以使用DP动态规划，记录每个生成的超级ugly number

==> Dp 数组 和 下标的含义 DP state: dp[i] 表示第 i + 1 个超级丑数，第 1 个超级丑数是 dp[0]，第 2 个超级丑数是 dp[1]
==> 递推公式: dp[i] = Math.min(dp[i], dp[bucketSort[j]] * primes[j]); //每次更新当前的DP值
==> DP数组的初始化 DP start:  dp[0] = 1; //DP初始化，首先把最小的丑数1放入到dp队列中


==》 bucketSort[] array 的作用： 选超级丑数的过程就相当于有 primes.length 这么多指针，
    ==> 在超级丑数列表上前进，一开始都在下标 0，然后 每一次可能选出多干个指针, 让它们各前进一步，不回头。
    
    如果下一个超级丑数要选择 primes[j] 作为质因数，基于之前的哪一个丑数，选过之后就移到下一个丑数（不能移到下下一个，因为下下一个肯定更大）。
 
 Leetcode: https://leetcode.cn/problems/super-ugly-number/solution/dong-tai-gui-hua-java-by-liweiwei1419-1yna/
*/
//Time: O(n*m) n是需要输出的第nth个丑数 m是primes[]的长度
//Space: O(n + m) bucketSort[] array是m size的空间复杂度， DP队列是n size的空间复杂度 ==》所以总的是 O (n + m)
class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        //ugly number的特性，每次最小的数 * primes number里面的数 得到的数一定是ugly number
        int pLength = primes.length;  //空间复杂度m, m是primes[]的长度
     
         //相当于每个primens中number element都有自己单独的指针poingter 来统计使用的次数，
        // 从而确认这个primes number产生的下一个值需要基于dp的index,
        int[] bucketSort = new int[pLength]; //buckSort 用来统计primes中的每个number 使用的次数
      

        //DP state 状态定义：dp[i] 表示第 i + 1 个超级丑数，第 1 个超级丑数是 dp[0]，第 2 个超级丑数是 dp[1]
        int[] dp = new int[n]; //空间复杂度 n
        dp[0] = 1; //DP初始化，首先把最小的丑数1放入到dp队列中储存

        for(int i = 1; i < n; i++){
            dp[i] = Integer.MAX_VALUE; // 因为每次都要找到当前所以能得到值中的最小值，得先假设一个最大值比较
            for(int j = 0; j < pLength; j++){
                //每次储存最小的值
                //这里要检查是否大于0 ==> 因为当这个乘积大于Integer最大值的情况时，这个值会变成一个负数
                //而且我们要每次找到最小值的储存，所以超过最大值的情况可以不用考虑
                if(dp[bucketSort[j]] * primes[j] > 0){
                    dp[i] = Math.min(dp[i], dp[bucketSort[j]] * primes[j]); //每次更新当前的DP值
                }
            }

            //要检查此时加入的是哪个丑数*primes[i]得到的，然后移动对应在bucketSort中的指针
            for(int j = 0; j < pLength; j++){
                if(dp[i] == dp[bucketSort[j]] * primes[j]){
                    // 这里不止执行一次，
                    // 例如选出 14 的时候，2 和 7 对应的最小丑数下标都要加 1
                    bucketSort[j]++;
                }
            }
        }

        return dp[n - 1]; //最后返回第n个ugly number
    }
}
