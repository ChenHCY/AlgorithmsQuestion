/* Leetcode 696. Count Binary Substrings
Given a binary string s, return the number of non-empty substrings that 
have the same number of 0's and 1's, and all the 0's and all the 1's 
in these substrings are grouped consecutively.

Substrings that occur multiple times are counted the number of times they occur.
Example 1:
Input: s = "00110011"
Output: 6
Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's: "0011", "01", "1100", "10", "0011", and "01".
Notice that some of these substrings repeat and are counted the number of times they occur.
Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.

Example 2:
Input: s = "10101"
Output: 4
Explanation: There are 4 substrings: "10", "01", "10", "01" that have equal number of consecutive 1's and 0's.
 
Constraints:
1 <= s.length <= 10^5
s[i] is either '0' or '1'.
*/

// Time: O(n) Space: O(1)

// 两个连续组之间的有效子串数量等于它们长度的最小值
// 因为我们只能组成与较小组所允许的数相同的对。如果我们有 5 个 0 后面跟着 2 个 1，我们只能组成 2 个有效的子串（0"01"和"0011"1），而不是 5 个。
// 因此，我们无需检查每个子字符串，
// 1. 计算连续相同字符组的数量
// 2. 对于每一对相邻的组，min(prevGroup_length, currGroup_length)在我们的答案中加上相应的数

// 跟踪三个值——先前组数(prevGroup)、当前组数(currGroup) 和 结果 (res)
// 从左到右扫描——当字符发生变化时，我们就找到了组边界。
// 添加有效子字符串——在每个边界处，将其添加min(prevCount, currCount)到结果中
// 更新计数- 前一次更新将变为当前更新，当前更新重置为 1
// 别忘了循环结束后的最后一组元素

class Solution {
    public int countBinarySubstrings(String s) {
        int res = 0;
        int prev = 0;
        int cur = 1;
        
        for(int i = 1; i < s.length(); i++){
            if(s.charAt(i - 1) != s.charAt(i)){ //the cur element is not same with previous element
                res += Math.min(prev, cur);
                prev = cur;
                cur = 1;
            } else{ //there is same, move cur pointer into next element
                cur++;
            }
        }
        
        res += Math.min(prev, cur);
        return res;
    }
}
