/*Leetcode  (Biweekly Contest 99) 2578 Split With Minimum Sum

Given a positive integer num, split it into two non-negative integers num1 and num2 such that:

The concatenation of num1 and num2 is a permutation of num.
In other words, the sum of the number of occurrences of each digit in num1 and num2 is equal to the number of occurrences of that digit in num.
num1 and num2 can contain leading zeros.
Return the minimum possible sum of num1 and num2.

Notes:

It is guaranteed that num does not contain any leading zeros.
The order of occurrence of the digits in num1 and num2 may differ from the order of occurrence of num.
 

Example 1:

Input: num = 4325
Output: 59
Explanation: We can split 4325 so that num1 is 24 and num2 is 35, giving a sum of 59. We can prove that 59 is indeed the minimal possible sum.


Example 2:

Input: num = 687
Output: 75
Explanation: We can split 687 so that num1 is 68 and num2 is 7, which would give an optimal sum of 75.
 

Constraints:

10 <= num <= 109
*/

/* 思路： 因为题意是找到组成最小num1和num2总和的分割

通过stringbuilder来组成数字

从后面往前遍历，如果 index（位置）是偶数 ==》 加入到num2Str
 
               如果 index（位置）是奇数 ==》 加入到num1Str
               
最后把stringBuilder转化位Integer的值，相加得到答案

*/
//Time: O(n^2)   Space: o(n)
class Solution {
    public int splitNum(int num) {
        //change integer number to be char[] array
        String numStr = String.valueOf(num);
        char[] arr = numStr.toCharArray();
        
        //sort the array
        Arrays.sort(arr); //Time: O(n)
        
        StringBuilder num1S = new StringBuilder();
        StringBuilder num2S = new StringBuilder();
        
        for(int i = arr.length - 1; i >= 0; i--){
            if(i % 2 == 0){
                num2S.insert(0, arr[i]);
            } else{
                num1S.insert(0, arr[i]);
            }
        }
        
        int num1 = Integer.parseInt(num1S.toString());
        int num2 = Integer.parseInt(num2S.toString());
        
        return num1 + num2;
    }
}
