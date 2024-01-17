/* 1047. Remove All Adjacent Duplicates In String

You are given a string s consisting of lowercase English letters. A duplicate removal consists of choosing two adjacent and equal letters and removing them.

We repeatedly make duplicate removals on s until we no longer can.

Return the final string after all such duplicate removals have been made. It can be proven that the answer is unique.

Example 1:
Input: s = "abbaca"
Output: "ca"
Explanation: 
For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".

Example 2:
Input: s = "azxxzy"
Output: "ay"
 

Constraints:
1 <= s.length <= 10^5
s consists of lowercase English letters.
*/
//Time: O(N)  Space: O(N)
class Solution {
    public String removeDuplicates(String s) {
        StringBuilder sb = new StringBuilder(s);
        int[] freq = new int[sb.length()];

        //遍历整个字符串s
        for(int i = 0; i < sb.length(); i++){
            //如果是首字母，或者相邻的不一样
            if(i == 0 || sb.charAt(i) != sb.charAt(i - 1)){
                freq[i] = 1; //统计为1
            } else{ //遇见相邻连续一样的字母
                freq[i] = freq[i - 1] + 1;
                //如果相邻两个一样的字母，使用stringbuilder.delete(s, e)进行删除
                if(freq[i] == 2){
                    sb.delete(i - 2 + 1, i + 1); //左闭右开区间
                    i = i - 2; //重置i指针回到删除区域之前
                }
            }
        }

        return sb.toString();
    }
}
