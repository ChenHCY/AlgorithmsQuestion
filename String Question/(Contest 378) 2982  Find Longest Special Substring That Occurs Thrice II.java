/* 2982. Find Longest Special Substring That Occurs Thrice II

You are given a string s that consists of lowercase English letters.

A string is called special if it is made up of only a single character. For example, the string "abc" is not special, whereas the strings "ddd", "zz", and "f" are special.

Return the length of the longest special substring of s which occurs at least thrice, or -1 if no special substring occurs at least thrice.

A substring is a contiguous non-empty sequence of characters within a string.

Example 1:
Input: s = "aaaa"
Output: 2
Explanation: The longest special substring which occurs thrice is "aa": substrings "aaaa", "aaaa", and "aaaa".
It can be shown that the maximum length achievable is 2.

Example 2:
Input: s = "abcdef"
Output: -1
Explanation: There exists no special substring which occurs at least thrice. Hence return -1.

Example 3:
Input: s = "abcaba"
Output: 1
Explanation: The longest special substring which occurs thrice is "a": substrings "abcaba", "abcaba", and "abcaba".
It can be shown that the maximum length achievable is 1.
 
Constraints:
3 <= s.length <= 5 * 10^5
s consists of only lowercase English letters.
*/

/* 首先统计每个字母的特殊字符串(单个连续)的长度list
    ==> example: aaaabbbabb => a: [4, 1]  b: [3, 2]
  然后分类讨论：设定每个字母的特殊字符串长度list为 L[]
  1. 最长的特殊字符串的长度为 L[0]: 那么可以选择3个长度为 L[0] - 2 的相同特殊子字符串
  2. 第二长的特殊字符串长度为 L[1]: ==> min(L[0] - 1, L[1]);
    ==> 如果 L[0] == L[1]，可以选择3个长度为 L[0] - 1 的特殊子字符串
    ==> 如果 L[0] > L[1], 可以选择3个长度 L[1] 的特殊子字符串
  3. 第三长的特殊子字符串的长度为 L[2]:
    ==> 可以选择3个长度为 L[2] 的特殊子字符串
*/
// Time: O(nlogn): 其中 n 为 s 的长度。
// Space: O(n)
class Solution {
    public int maximumLength(String s) {
        char[] charArr = s.toCharArray(); //把字符串改成char[] array
        //使用邻接表来储存每个字母特殊字符串(单个连续)的长度list
        List<Integer>[] groups = new ArrayList[26];
        Arrays.setAll(groups, i -> new ArrayList<>()); //给每个字母开空间

        int count = 0;
        for(int i = 0; i < charArr.length; i++){
            count++;
            //如果到达最后，或者遇到不同的字母
            if(i == charArr.length - 1 || charArr[i] != charArr[i + 1]){
                groups[charArr[i] - 'a'].add(count); //把统计的连续字符串长度加入到对应字母list
                count = 0; //更新count为0
            }
        }

        int res = 0;
        //遍历每个字母的特殊字符串长度，然后进行分类讨论
        for(List<Integer> l : groups){
            if(l.isEmpty()){
                continue; //如果不存在这个字母的特殊字符串，跳过后续步骤
            }
            l.sort(Collections.reverseOrder()); //降序排列子字符串的长度list
            //因为有三种情况，所以这个list需要至少三个值，我们要加入两个值 避免超出list限度
            l.add(0);
            l.add(0);
            res = Math.max(res, Math.max(l.get(0) - 2, Math.max(Math.min(l.get(0) - 1, l.get(1)), l.get(2))));
        }

        return res > 0 ? res : -1; //检查是否有出现3次以上的特殊字符串
    }
}
