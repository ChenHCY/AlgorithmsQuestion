/* Leetcode 1903. Largest Odd Number in String

You are given a string num, representing a large integer. Return the largest-valued odd integer (as a string) that is a non-empty substring of num, or an empty string "" if no odd integer exists.

A substring is a contiguous sequence of characters within a string.

Example 1:

Input: num = "52"
Output: "5"
Explanation: The only non-empty substrings are "5", "2", and "52". "5" is the only odd number.

Example 2:

Input: num = "4206"
Output: ""
Explanation: There are no odd numbers in "4206".

Example 3:

Input: num = "35427"
Output: "35427"
Explanation: "35427" is already an odd number.
 

Constraints:
1 <= num.length <= 10^5
num only consists of digits and does not contain any leading zeros.
*/

class Solution {
    // 如果一个string的最后一位是奇数，这个子字符串一定是奇数
    // 因为我们要找到一个子字符串是最大的奇数，所以需要从右往左遍历，直接碰见的第一个奇数(i)
    // [0, i]组成的子字符串就是这个字符串中具有最大奇数值的答案
    public String largestOddNumber(String num) {
        int n = num.length();
        //如果最后一位就是奇数，整个字符串就是答案
        if((int)num.charAt(n - 1) % 2 == 1){
            return num;
        }

        int index = n - 1; //从右往左遍历
        while(index >= 0){
            if((int)num.charAt(index) % 2 == 1){
                return num.substring(0, index+1);
            } else{
                index--;
            }
        }

        return ""; //如果一个奇数没有 输出""
    }
}
