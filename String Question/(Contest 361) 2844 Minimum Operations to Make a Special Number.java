/*Leetcode (Contest 361) 2844 Minimum Operations to Make a Special Number

You are given a 0-indexed string num representing a non-negative integer.

In one operation, you can pick any digit of num and delete it. Note that if you delete all the digits of num, num becomes 0.

Return the minimum number of operations required to make num special.

An integer x is considered special if it is divisible by 25.

Example 1:

Input: num = "2245047"
Output: 2
Explanation: Delete digits num[5] and num[6]. The resulting number is "22450" which is special since it is divisible by 25.
It can be shown that 2 is the minimum number of operations required to get a special number.

Example 2:

Input: num = "2908305"
Output: 3
Explanation: Delete digits num[3], num[4], and num[6]. The resulting number is "2900" which is special since it is divisible by 25.
It can be shown that 3 is the minimum number of operations required to get a special number.

Example 3:

Input: num = "10"
Output: 1
Explanation: Delete digit num[0]. The resulting number is "0" which is special since it is divisible by 25.
It can be shown that 1 is the minimum number of operations required to get a special number.

 
Constraints:
1 <= num.length <= 100
num only consists of digits '0' through '9'.
num does not contain any leading zeros.
*/

//Time: O(n * m)    Space: O(1)
class Solution {
    //此题是求 删除最少的数字，让num可以变成一个能整除25的新数字
    // 如果一个数能被 25 整除，它的最后两位数 有五种情况：0 00 25 50 75
    // ==> 所以我们从右往左进行遍历： 检查 每 两个index对应数字的组合 ==> 能否整除25
    // ==> 然后比较 要删除多少位数字 可以让 num 末尾获得该组合。
    // ==> 也就是删除这两个数字右侧所有的数字 ==>  右侧长度（num.length() - index2) - 2（保留这个组合数字）
    public int minimumOperations(String num) {
        int res = num.length();
        int n = num.length();
        
        for(int i = n - 1; i >= 0; i--){
            // 所以我们从右往左进行遍历： 检查 每 两个index对应数字的组合 ==> 能否整除25
            for(int j = i - 1; j >= 0; j--){
                int num25 = (num.charAt(i) - '0') + (num.charAt(j) - '0') * 10;
                // ==> 能否整除25
                if(num25 % 25 == 0){
                    // 然后比较 要删除多少位数字 可以让 num 末尾获得该组合
                    res = Math.min(res, n - j - 2);
                }
            }
            //如果 num 中有 0，那么只保留0也是可以整除25，==> 所以也就是需要删除 除0之外的所有数字（n-1）
            if(num.charAt(i) == '0'){
                res = Math.min(res, n - 1);
            }
        }
        
        return res;
    }
}
