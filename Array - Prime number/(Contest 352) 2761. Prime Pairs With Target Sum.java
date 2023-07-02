/*Leetcode (Contest 352)  2761. Prime Pairs With Target Sum

You are given an integer n. We say that two integers x and y form a prime number pair if:

      · 1 <= x <= y <= n
      · x + y == n
      · x and y are prime numbers

Return the 2D sorted list of prime number pairs [xi, yi]. The list should be sorted in increasing order of xi. If there are no prime number pairs at all, return an empty array.

Note: A prime number is a natural number greater than 1 with only two factors, itself and 1.

Example 1:

Input: n = 10
Output: [[3,7],[5,5]]
Explanation: In this example, there are two prime pairs that satisfy the criteria. 
These pairs are [3,7] and [5,5], and we return them in the sorted order as described in the problem statement.

Example 2:

Input: n = 2
Output: []
Explanation: We can show that there is no prime number pair that gives a sum of 2, so we return an empty array. 
 

Constraints:
1 <= n <= 10^6
*/

class Solution {
    /*埃拉托斯特尼筛法（埃氏筛）是一种用于枚举质数的算法。
     它的基本思想是从2开始，将每个素数的倍数标记为非素数，直到遍历完所有小于给定上限的数。

     质数：质数是大于 1 的自然数，并且只有两个因子，即它 本身 和 1 。
    */

    private final static int MAX = (int) 1e6;
    private final static HashSet<Integer> set = new HashSet<>(); //用来储存范围内的所有质数
    static{
        boolean[] isPrime = new boolean[MAX + 1];
        //2是质数，则将其后面2的倍数的数字删除，因为他们都是2的倍数，所以不可能是质数，对于3，则删除3的倍数，
        //==> 以此类推，后面无法删除的数字则是质数
        for(int i = 2; i <= MAX; i++){
            if(!isPrime[i]){
                set.add(i);// 把得到的质数 加入到Hashset中储存
                for(int j = i; j <= MAX / i; j++){
                    isPrime[j * i] = true; //如果存在新的因数，则标记true
                }
            }
        }
    }
    
    //对于一个数字i，若i为质数且n-i也为质数，则这两个数字为满足题目要求的一个质数对
    //那么只需要在n/2的一半范围内求解即可
    public List<List<Integer>> findPrimePairs(int n) {
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 2; i <= n/2; i++){
            if(set.contains(i) && set.contains(n - i) && i <= n - i){
                res.add(Arrays.asList(i, n - i));
            }
        }
        return res;
    }
}
