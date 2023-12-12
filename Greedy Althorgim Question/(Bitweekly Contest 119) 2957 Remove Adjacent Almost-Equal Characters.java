/* Leetcode 2957. Remove Adjacent Almost-Equal Characters

You are given a 0-indexed string word.

In one operation, you can pick any index i of word and change word[i] to any lowercase English letter.

Return the minimum number of operations needed to remove all adjacent almost-equal characters from word.

Two characters a and b are almost-equal if a == b or a and b are adjacent in the alphabet.

Example 1:
Input: word = "aaaaa"
Output: 2
Explanation: We can change word into "acaca" which does not have any adjacent almost-equal characters.
It can be shown that the minimum number of operations needed to remove all adjacent almost-equal characters from word is 2.

Example 2:
Input: word = "abddez"
Output: 2
Explanation: We can change word into "ybdoez" which does not have any adjacent almost-equal characters.
It can be shown that the minimum number of operations needed to remove all adjacent almost-equal characters from word is 2.

Example 3:
Input: word = "zyxyxyz"
Output: 3
Explanation: We can change word into "zaxaxaz" which does not have any adjacent almost-equal characters. 
It can be shown that the minimum number of operations needed to remove all adjacent almost-equal characters from word is 3.
 

Constraints:
1 <= word.length <= 100
word consists only of lowercase English letters.
*/
// Time: O(n)   Space: O(1)
class Solution {
    // 判断修改至少多少次，能保证word字符串中 不存在任何相邻字母近似相等
    // 相邻字母近似相等：字母一样 或者 字母在字典序中相邻
    // 解题思路：贪心思路
    // 如果 word[i−1] 和 word[i]是 近似相等，如果修改左边，word[i] 和 word[i+1] 还是存在相邻近似相等的可能性
    // ==> 所以需要修改右边的字母 word[i], 减少修改的次数
    public int removeAlmostEqualCharacters(String word) {
        int res = 0;
        
        for(int i = 1; i < word.length(); i++){
            //发现相邻近似相等的字母
            if(Math.abs(word.charAt(i) - word.charAt(i - 1)) <= 1){
                res += 1; //统计
                i++; //因为已经修改一次，可以多移动一个，检查后续的字母
            }
        }

        return res;
    }
}
