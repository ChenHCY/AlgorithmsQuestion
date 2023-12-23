/* 1422. Maximum Score After Splitting a String

Given a string s of zeros and ones, return the maximum score after splitting the string into two non-empty 
substrings (i.e. left substring and right substring).

The score after splitting a string is the number of zeros in the left substring plus the number of ones in the right substring.

Example 1:
Input: s = "011101"
Output: 5 
Explanation: 
All possible ways of splitting s into two non-empty substrings are:
left = "0" and right = "11101", score = 1 + 4 = 5 
left = "01" and right = "1101", score = 1 + 3 = 4 
left = "011" and right = "101", score = 1 + 2 = 3 
left = "0111" and right = "01", score = 1 + 1 = 2 
left = "01110" and right = "1", score = 2 + 1 = 3

Example 2:
Input: s = "00111"
Output: 5
Explanation: When left = "00" and right = "111", we get the maximum score = 2 + 3 = 5

Example 3:
Input: s = "1111"
Output: 3
 
Constraints:
2 <= s.length <= 500
The string s consists of characters '0' and '1' only.
*/
//Time: O(n)   Space: O(n)
class Solution {
    // 前缀和，计算储存每个index前缀和值，也就能得到当前index之前有多少个1 
  //  ==> 然后我们就能得到当前位置  左边字符串0的个数，和右边字符串1的个数
    // 遍历每个切割点，计算最大的和
    public int maxScore(String s) {
        int n = s.length();
        int[] prefix = new int[n + 1];
        int res = 0;

        //前缀和，计算储存每个index位置的值 ==> 也就是当前位置之前有多少个1
        for(int i = 1; i <= n; i++){
            prefix[i] = prefix[i - 1] + (s.charAt(i - 1) - '0');
        }

        //遍历每个切割点，计算每个index 左边0和右边1的数量和
        for(int i = 1; i < n; i++){
            // i表示当前位置左边num总数量，prefix[i]当前位置左边1的数量
            int left = i - prefix[i]; //相减得到当前位置左边字符串0的数量
            
            //prefix[n]总共1的数量，prefix[i]当前位置左边1的数量
            int right = prefix[n] - prefix[i];  //相减得到当前位置右边字符串1的数量
            
            //在每个切割点index进行更新，找到最大值
            res = Math.max(res, left + right); 
        }
        
        return res;
    }
}
