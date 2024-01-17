/* 1209. Remove All Adjacent Duplicates in String II

You are given a string s and an integer k, a k duplicate removal consists of choosing k adjacent and equal letters from s 
and removing them, causing the left and the right side of the deleted substring to concatenate together.

We repeatedly make k duplicate removals on s until we no longer can.

Return the final string after all such duplicate removals have been made. It is guaranteed that the answer is unique.

Example 1:
Input: s = "abcd", k = 2
Output: "abcd"
Explanation: There's nothing to delete.

Example 2:
Input: s = "deeedbbcccbdaa", k = 3
Output: "aa"
Explanation: 
First delete "eee" and "ccc", get "ddbbbdaa"
Then delete "bbb", get "dddaa"
Finally delete "ddd", get "aa"

Example 3:
Input: s = "pbbcggttciiippooaais", k = 2
Output: "ps"
 
Constraints:
1 <= s.length <= 10^5
2 <= k <= 10^4
s only contains lowercase English letters.
*/

// 思路：使用bucketSort统计每个字母连续出现的数量，如果大于k次，表示需要删除这个区域
// 使用 stringbuilder的内置function sb.delete(startrange, endrange) 删除这个区域，重置i指针到删除区域之前
//Time: O(n)  Space: O(n) 
class Solution {
    public String removeDuplicates(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        int[] count = new int[sb.length()];

        for(int i = 0; i < sb.length(); i++){
            //如果是开头字母，或者和前一位的字母不一样，则记录为出现一次
            if(i == 0 || sb.charAt(i) != sb.charAt(i - 1)){
                count[i] = 1;
            } else{ //如果遇见相邻一样的字母，bucketSort记录数量
                count[i] = count[i - 1] + 1;
                //然后检查数量有没有超过k次，超过使用sb.delete()进行删除
                if(count[i] == k){
                    sb.delete(i - k + 1, i + 1); //连续的k个字母都需要删除
                    i = i - k; //重置i指针到删除区域之前
                }
            }
        }

        return sb.toString(); //完成修改
    }
}
