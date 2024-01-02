/* 779. K-th Symbol in Grammar

We build a table of n rows (1-indexed). We start by writing 0 in the 1st row. Now in every subsequent row, we look at the 
previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.

  · For example, for n = 3, the 1st row is 0, the 2nd row is 01, and the 3rd row is 0110.

Given two integer n and k, return the kth (1-indexed) symbol in the nth row of a table of n rows.

Example 1:
Input: n = 1, k = 1
Output: 0
Explanation: row 1: 0

Example 2:
Input: n = 2, k = 1
Output: 0
Explanation: 
row 1: 0
row 2: 01

Example 3:
Input: n = 2, k = 2
Output: 1
Explanation: 
row 1: 0
row 2: 01
 

Constraints:
1 <= n <= 30
1 <= k <= 2^n - 1
*/
// 时间复杂度：O(n) 
// 空间复杂度：忽略递归带来的额外空间开销，复杂度为 O(1) 
class Solution {
    //迭代，因为每一行生成的String都是基于前一行的部分
    // 0 ==> 01 | 1 ==> 10 : 所以我们可以迭代查找前面行的字符串情况来得到答案
    public int kthGrammar(int n, int k) {
        //exit condition, 到达第一行
        if(n == 1){
            return 0;
        }

        int preNum = kthGrammar(n - 1, (k + 1) / 2); //查找前一行的字符是什么

        //然后判断当前行的第kth个字符是什么
        if(preNum == 0){ //如果之前行的num是0
            return k % 2 == 1 ? 0 : 1; // ==> 0: 01
        } else if(preNum == 1){ //如果之前行的num是1
            return k % 2 == 1 ? 1 : 0; // ==> 1: 10
        }

        return -1;
    }
}
